package com.smart.life.saas.domain.core.fleet;

import com.smart.life.kernel.JourneyException;
import com.smart.life.saas.domain.common.FileStorageService;
import com.smart.life.saas.infrastructure.core.fleet.FleetModelConstants;
import com.smart.life.saas.web.fleet.dto.FleetModelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Optional;

@Service
public class FleetModelService {

    private final FleetModelRepository fleetModelRepository;
    private final FileStorageService fileStorageService;

    public FleetModelService(FleetModelRepository fleetModelRepository, FileStorageService fileStorageService) {
        this.fleetModelRepository = fleetModelRepository;
        this.fileStorageService = fileStorageService;
    }

    /**
     * Validate a {@link FleetModelDTO} : fleet model creation
     *
     * @param fleetModelDTO: a fleet model DTO
     */
    private void validateCreate(FleetModelDTO fleetModelDTO) {
        boolean modelExists = fleetModelRepository.existsByNameAndIdNot(fleetModelDTO.getName(), -1L);
        if (modelExists) {
            throw JourneyException
                    .preconditionFailed(String.format("Fleet model with name: %s already exists", fleetModelDTO.getName()));
        }
    }

    /**
     * Create a new fleet model
     *
     * @param fleetModelDTO: a fleet model DTO
     * @return {@link FleetModel} a fleet model instance
     */
    public FleetModel create(FleetModelDTO fleetModelDTO) {
        validateCreate(fleetModelDTO);

        Path filePath = fileStorageService
                .resolveFilePath(FleetModelConstants.MODEL_THUMB_IMAGE_PATH, fleetModelDTO.getThumbImageFile().getOriginalFilename());

        Path realFilePath = fileStorageService.saveFile(fleetModelDTO.getThumbImageFile(), filePath);

        FleetModel model = FleetModel.builder().name(fleetModelDTO.getName()).thumbImage(realFilePath.getFileName().toString())
                .active(fleetModelDTO.isActive()).build();
        return fleetModelRepository.save(model);
    }

    /**
     * Find all fleet models by pagination
     *
     * @param pageable: a {@link Pageable} instance
     * @return {@link Page<FleetModel>} a fleet model paginate instance
     */
    public Page<FleetModel> findAll(Pageable pageable) {
        return fleetModelRepository.findAll(pageable);
    }

    /**
     * Find a fleet model base on a given id
     *
     * @param id: a fleet model identifier
     * @return {@link Optional<FleetModel>} a optional fleet model instance
     */
    public Optional<FleetModel> findById(Long id) {
        return fleetModelRepository.findById(id);
    }

    public boolean existById(Long id) {
        return fleetModelRepository.existById(id);
    }
}
