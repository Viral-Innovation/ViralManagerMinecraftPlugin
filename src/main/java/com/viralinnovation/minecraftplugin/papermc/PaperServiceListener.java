package com.viralinnovation.minecraftplugin.papermc;

import com.viralinnovation.viralmanager.api.architecture.ArchitectureEntity;
import com.viralinnovation.viralmanager.api.services.ServiceListener;
import com.viralinnovation.viralmanager.api.services.record.ServiceEntity;
import com.viralinnovation.viralmanager.api.utils.ViralFileUtils;

import java.io.FileWriter;
import java.io.IOException;

public class PaperServiceListener implements ServiceListener {

    @Override
    public void beforeInit(ArchitectureEntity architecture, ServiceEntity serviceEntity) {
        try {
            FileWriter stream = new FileWriter(
                    ViralFileUtils.generateOSBasedClassPath(
                            ViralFileUtils.generateServiceFolderFilePath(architecture.getId()), "eula.txt"));
            stream.write("eula=true");
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterInit(ArchitectureEntity architecture, ServiceEntity serviceEntity) {

    }

    @Override
    public void beforeStop(ArchitectureEntity architecture, ServiceEntity serviceEntity) {

    }

    @Override
    public void afterStop(ArchitectureEntity architecture, ServiceEntity serviceEntity) {

    }
}
