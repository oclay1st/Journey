package com.smart.life.saas.domain.core.fleet;

import com.smart.life.kernel.JourneyException;
import com.smart.life.saas.domain.common.FileStorageService;
import com.smart.life.saas.infrastructure.core.fleet.FleetConstants;
import com.smart.life.saas.web.fleet.dto.FleetDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FleetService {

    private final FleetRepository fleetRepository;
    private final FleetModelService fleetModelService;
    private final FileStorageService fileStorageService;


    /**
     * Validate fleetDTO on fleet creation
     *
     * @param fleetDTO: {@link FleetDTO} a fleet DTO
     */
    public void validateCreation(FleetDTO fleetDTO) {
        if (!fleetModelService.existById(fleetDTO.getModelId())) {
            throw JourneyException.notFound(String.format("Fleet model with id %s doesn't exists", fleetDTO.getModelId()));
        }

        boolean fleetExists = fleetRepository.existByNumberAndIdNot(fleetDTO.getNumber(), -1L);
        if (fleetExists) {
            throw JourneyException.preconditionFailed("Fleet with number: " + fleetDTO.getNumber() + " already exists");
        }
    }

    /**
     * Create a fleet based on a given fleet DTO
     *
     * @param fleetDTO: {@link FleetDTO} a fleet DTO
     * @return {@link Fleet}: a fleet domain model
     */
    @Transactional
    public Fleet create(FleetDTO fleetDTO) {
        validateCreation(fleetDTO);
        List<String> imageNames = new ArrayList<>();
        for (MultipartFile image : fleetDTO.getImageFiles()) {
            Path path = fileStorageService.resolveFilePath(FleetConstants.FLEET_IMAGE_PATH, image.getOriginalFilename());
            path = fileStorageService.saveFile(image, path);
            imageNames.add(path.getFileName().toString());
        }
        Fleet fleet = new Fleet();
        fleet.setNumber(fleetDTO.getNumber());
        FleetModel fleetModel = new FleetModel();
        fleetModel.setId(fleetDTO.getModelId());
        fleet.setModel(fleetModel);
        fleet.setActive(fleetDTO.isActive());
        fleet.setCapacity(fleetDTO.getCapacity());
        fleet.setMaxLuggage(fleetDTO.getMaxLuggage());
        fleet.setImages(imageNames);
        return fleetRepository.save(fleet);
    }

    /**
     * Find all fleets by pagination
     *
     * @param pageable: a pageable request param
     * @return {@link Page<Fleet>}: a page of fleets
     */
    public Page<Fleet> findAll(Pageable pageable) {
        return fleetRepository.findAll(pageable);
    }

    /**
     * Find a {@link Fleet} by id
     *
     * @param id: a fleet identifier
     * @return {@link Fleet}: a fleet domain model
     */
    public Optional<Fleet> findById(Long id) {
        return fleetRepository.findById(id);
    }
}
