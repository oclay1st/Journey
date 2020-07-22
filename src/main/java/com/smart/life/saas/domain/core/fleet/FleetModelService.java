package com.smart.life.saas.domain.core.fleet;

import com.smart.life.kernel.JourneyException;
import com.smart.life.saas.domain.common.FileStorageService;
import com.smart.life.saas.web.fleet.dto.FleetModelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class FleetModelService {

    private final FleetModelRepository fleetModelRepository;
    private final FileStorageService fileStorageService;

    public FleetModelService(FleetModelRepository fleetModelRepository, FileStorageService fileStorageService) {
        this.fleetModelRepository = fleetModelRepository;
        this.fileStorageService = fileStorageService;
    }

    private void validateCreate(FleetModelDTO fleetModelDTO) {
        boolean modelExists = fleetModelRepository.existsByNameAndIdNot(fleetModelDTO.getName(), -1L);
        if (modelExists) {
            throw new JourneyException();
        }
    }

    public FleetModel create(FleetModelDTO fleetModelDTO) {
        validateCreate(fleetModelDTO);

        Path filePath = fileStorageService
                .resolveFilePath(Constants.MODEL_THUMB_IMAGE_PATH, fleetModelDTO.getThumbImageFile().getOriginalFilename());

        Path realFilePath = fileStorageService.saveFile(fleetModelDTO.getThumbImageFile(), filePath);

        FleetModel model = FleetModel.builder().name(fleetModelDTO.getName()).thumbImage(realFilePath.getFileName().toString())
                .active(fleetModelDTO.isActive()).build();
        return fleetModelRepository.save(model);
    }

    public Page<FleetModel> findAll(Pageable pageable) {
        return fleetModelRepository.findAll(pageable);
    }
}
