package com.viralinnovation.minecraftplugin;

import com.viralinnovation.minecraftplugin.papermc.*;
import com.viralinnovation.viralmanager.api.architecture.JVMEntity;
import com.viralinnovation.viralmanager.api.plugins.Plugin;
import com.viralinnovation.viralmanager.api.plugins.Registry;

public final class MinecraftPlugin extends Plugin {

    @Override
    public void register(Registry registry) {
        registry.addMapping("Paper MC Service", PaperFormEntity.class, PaperServiceResponse.class, PaperServiceEntity.class,
                JVMEntity.class, PaperServiceRepository.class, PaperServiceListener.class, getPluginConfig(),  getClass().getResource("/images/paperservice.png"));
    }

    @Override
    public void disable() {

    }
}
