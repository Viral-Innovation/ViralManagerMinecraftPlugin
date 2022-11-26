package com.viralinnovation.minecraftplugin.papermc;

import com.viralinnovation.minecraftplugin.paperapi.PaperAPIManager;
import com.viralinnovation.viralmanager.api.annotations.form.Data;
import com.viralinnovation.viralmanager.api.annotations.form.Dropdown;
import com.viralinnovation.viralmanager.api.annotations.form.DropdownOption;
import com.viralinnovation.viralmanager.api.architecture.ArchitectureEntity;
import com.viralinnovation.viralmanager.api.architecture.JVMEntity;
import com.viralinnovation.viralmanager.api.services.ServiceType;
import com.viralinnovation.viralmanager.api.services.form.FormEntity;
import com.viralinnovation.viralmanager.api.services.record.ServiceEntity;
import com.viralinnovation.viralmanager.api.utils.ViralFileUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class PaperFormEntity extends FormEntity {

    @Data(name = "Max. RAM (GB)")
    private int xmx;

    @Data(name = "Minecraft Version")
    @Dropdown(value = {
            @DropdownOption(value = "Minecraft 1.8.8", id="8"),
            @DropdownOption(value = "Minecraft 1.16.5", id="16"),
            @DropdownOption(value = "Minecraft 1.17.1", id="17"),
            @DropdownOption(value = "Minecraft 1.18.2", id="18"),
            @DropdownOption(value = "Minecraft 1.19.X", id="19")
    })
    private String mcVersion;

    public PaperFormEntity() {super();}

    public PaperFormEntity(int xmx, String mcVersion) {
        this.xmx = xmx;
        this.mcVersion = mcVersion;
    }

    public String getMcVersion() {
        return mcVersion;
    }

    public int getXmx() {
        return xmx;
    }

    private int getJavaVersion(@NotNull PaperFormEntity formEntity) {
        if(Integer.parseInt(formEntity.getMcVersion()) <= 15) {
            return 8;
        } else if(Integer.parseInt(formEntity.getMcVersion()) == 16) {
            return 11;
        } else if(Integer.parseInt(formEntity.getMcVersion()) == 17) {
            return 16;
        } else {
            return 17;
        }
    }

    @Override
    public <T extends ArchitectureEntity> T getArchitectureEntity(@NotNull ServiceType<T, ? extends ServiceEntity> serviceType, FormEntity formEntity) {
        PaperFormEntity paperFormEntity = (PaperFormEntity) formEntity;
        PaperAPIManager paperAPIManager = new PaperAPIManager();
        String fileLocation = paperAPIManager.downloadBuild("paper", mcVersion, ViralFileUtils.generateUploadedFilePath(endpoint));
        return serviceType.getArchitectureEntity().cast(
                new JVMEntity(uuid, fileLocation, List.of("--nogui"), 1, paperFormEntity.getXmx(), getJavaVersion(paperFormEntity)));
    }

    @Override
    public <S extends ServiceEntity> S getServiceEntity(ServiceType<? extends ArchitectureEntity, S> serviceType, @NotNull FormEntity formEntity, ArchitectureEntity architectureEntity) {
        PaperFormEntity paperFormEntity = (PaperFormEntity) formEntity;
        JVMEntity jvmEntity = (JVMEntity) architectureEntity;
        return (S) new PaperServiceEntity(jvmEntity, formEntity.getName(), formEntity.getEndpoint(), serviceType, paperFormEntity.getMcVersion());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperFormEntity that = (PaperFormEntity) o;
        return xmx == that.xmx && mcVersion.equals(that.mcVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xmx, mcVersion);
    }

    @Override
    public String toString() {
        return "PaperFormEntity{" +
                "xmx=" + xmx +
                ", mcVersion=" + mcVersion +
                ", name='" + name + '\'' +
                ", endpoint=" + endpoint +
                '}';
    }
}
