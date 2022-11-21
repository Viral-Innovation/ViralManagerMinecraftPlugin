package com.viralinnovation.minecraftplugin.papermc;

import com.viralinnovation.viralmanager.api.annotations.form.Data;
import com.viralinnovation.viralmanager.api.architecture.ArchitectureEntity;
import com.viralinnovation.viralmanager.api.architecture.JVMEntity;
import com.viralinnovation.viralmanager.api.services.ServiceType;
import com.viralinnovation.viralmanager.api.services.form.FormEntity;
import com.viralinnovation.viralmanager.api.services.record.ServiceEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class PaperFormEntity extends FormEntity {

    @Data(name = "Max. RAM (GB)")
    private int xmx;

    @Data(name = "Minecraft Version")
    private int mcVersion;

    public PaperFormEntity() {super();}

    public PaperFormEntity(int xmx, int mcVersion) {
        this.xmx = xmx;
        this.mcVersion = mcVersion;
    }

    public int getMcVersion() {
        return mcVersion;
    }

    public int getXmx() {
        return xmx;
    }

    private int getJavaVersion(@NotNull PaperFormEntity formEntity) {
        if(formEntity.getMcVersion() <= 15) {
            return 8;
        } else if(formEntity.getMcVersion() == 16) {
            return 11;
        } else if(formEntity.getMcVersion() == 17) {
            return 16;
        } else {
            return 17;
        }
    }

    @Override
    public <T extends ArchitectureEntity> T getArchitectureEntity(ServiceType<T, ? extends ServiceEntity> serviceType, FormEntity formEntity) {
        PaperFormEntity paperFormEntity = (PaperFormEntity) formEntity;
        return serviceType.getArchitectureEntity().cast(
                new JVMEntity("D:\\GitHub\\ViralManager\\uploadedFiles\\d91a1697-1b13-40d5-a9f0-014f31f7d52a\\paper-1.19.2-210.jar",
                        List.of("--nogui"), 1, paperFormEntity.getXmx(), getJavaVersion(paperFormEntity)));
    }

    @Override
    public <S extends ServiceEntity> S getServiceEntity(ServiceType<? extends ArchitectureEntity, S> serviceType, FormEntity formEntity, ArchitectureEntity architectureEntity) {
        PaperFormEntity paperFormEntity = (PaperFormEntity) formEntity;
        JVMEntity jvmEntity = (JVMEntity) architectureEntity;
        return (S) new PaperServiceEntity(jvmEntity, formEntity.getName(), formEntity.getEndpoint(), serviceType, paperFormEntity.getMcVersion());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperFormEntity that = (PaperFormEntity) o;
        return xmx == that.xmx && mcVersion == that.mcVersion;
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
