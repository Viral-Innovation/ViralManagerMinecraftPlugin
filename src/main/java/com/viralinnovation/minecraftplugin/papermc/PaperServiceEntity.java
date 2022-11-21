package com.viralinnovation.minecraftplugin.papermc;

import com.viralinnovation.viralmanager.api.architecture.ArchitectureEntity;
import com.viralinnovation.viralmanager.api.architecture.JVMEntity;
import com.viralinnovation.viralmanager.api.services.ServiceType;
import com.viralinnovation.viralmanager.api.services.record.ServiceEntity;

import javax.persistence.Entity;
import java.util.Objects;
import java.util.UUID;

@Entity
public class PaperServiceEntity extends ServiceEntity {

    private int mcVersion;

    public PaperServiceEntity() {
        super();
        this.mcVersion = 19;
    }

    public PaperServiceEntity(JVMEntity jvmEntity, String name, UUID endpoint, ServiceType<? extends ArchitectureEntity, ? extends ServiceEntity> type, int mcVersion) {
        super(jvmEntity, name, endpoint, type);
        this.mcVersion = mcVersion;
    }

    public PaperServiceEntity(JVMEntity jvmEntity, String name, UUID endpoint, Boolean system, ServiceType<? extends ArchitectureEntity, ? extends ServiceEntity> type, int mcVersion) {
        super(jvmEntity, name, endpoint, system, type);
        this.mcVersion = mcVersion;
    }

    public int getMcVersion() {
        return mcVersion;
    }

    public void setMcVersion(int mcVersion) {
        this.mcVersion = mcVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperServiceEntity that = (PaperServiceEntity) o;
        return mcVersion == that.mcVersion;
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
}
