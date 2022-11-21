package com.viralinnovation.minecraftplugin.papermc;

import com.viralinnovation.viralmanager.api.architecture.JVMEntity;
import com.viralinnovation.viralmanager.api.services.response.ServiceResponse;

public class PaperServiceResponse extends ServiceResponse {

    private int xmx;
    private int mcVersion;

    public PaperServiceResponse(PaperServiceEntity paperServiceEntity, JVMEntity jvmEntity) {
        super(paperServiceEntity, jvmEntity);
        this.xmx = jvmEntity.getXmx();
        this.mcVersion = paperServiceEntity.getMcVersion();
    }

    public int getMcVersion() {
        return mcVersion;
    }

    public int getXmx() {
        return xmx;
    }
}
