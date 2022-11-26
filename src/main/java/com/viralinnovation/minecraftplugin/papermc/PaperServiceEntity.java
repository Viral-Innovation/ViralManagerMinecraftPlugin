package com.viralinnovation.minecraftplugin.papermc;

import com.viralinnovation.viralmanager.api.architecture.ArchitectureEntity;
import com.viralinnovation.viralmanager.api.architecture.JVMEntity;
import com.viralinnovation.viralmanager.api.services.ServiceType;
import com.viralinnovation.viralmanager.api.services.record.ServiceEntity;
import com.viralinnovation.viralmanager.api.services.response.ServiceResponse;

import javax.persistence.Entity;
import java.util.Objects;
import java.util.UUID;

@Entity
public class PaperServiceEntity extends ServiceEntity {

    private String mcVersion;

    public PaperServiceEntity() {
        super();
        this.mcVersion = "19";
    }

    public PaperServiceEntity(JVMEntity jvmEntity, String name, UUID endpoint, ServiceType<? extends ArchitectureEntity, ? extends ServiceEntity> type, String mcVersion) {
        super(jvmEntity, name, endpoint, type);
        this.mcVersion = mcVersion;
    }

    public PaperServiceEntity(JVMEntity jvmEntity, String name, UUID endpoint, Boolean system, ServiceType<? extends ArchitectureEntity, ? extends ServiceEntity> type, String mcVersion) {
        super(jvmEntity, name, endpoint, system, type);
        this.mcVersion = mcVersion;
    }

    public String getMcVersion() {
        return mcVersion;
    }

    public void setMcVersion(String mcVersion) {
        this.mcVersion = mcVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperServiceEntity that = (PaperServiceEntity) o;
        return mcVersion.equals(that.mcVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mcVersion);
    }

    @Override
    public String toString() {
        return "PaperServiceEntity{" +
                "mcVersion=" + mcVersion +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", isSystem=" + isSystem +
                ", endpoint=" + endpoint +
                '}';
    }

    @Override
    public ServiceResponse getResponse(ServiceEntity serviceEntity, ArchitectureEntity architectureEntity) {
        return new PaperServiceResponse((PaperServiceEntity) serviceEntity, (JVMEntity) architectureEntity);
    }
}
