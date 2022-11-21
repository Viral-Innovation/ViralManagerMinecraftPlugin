package com.viralinnovation.minecraftplugin.papermc;

import com.viralinnovation.viralmanager.api.services.repositories.ServiceRepository;
import com.viralinnovation.viralmanager.api.services.repositories.ServiceRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperServiceRepository extends ServiceRepository<PaperServiceEntity>, ServiceRepositoryCustom {
}
