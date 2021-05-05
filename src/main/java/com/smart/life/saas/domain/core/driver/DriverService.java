package com.smart.life.saas.domain.core.driver;

import com.smart.life.kernel.JourneyException;
import com.smart.life.saas.domain.common.FileStorageService;
import com.smart.life.saas.domain.core.user.UserService;
import com.smart.life.saas.domain.core.user.Role;
import com.smart.life.saas.domain.core.user.User;
import com.smart.life.saas.domain.core.user.UserRole;
import com.smart.life.saas.infrastructure.core.driver.DriverConstants;
import com.smart.life.saas.web.driver.dto.DriverDTO;
import com.smart.life.saas.web.driver.dto.DriverMapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Path;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DriverService {

    private final UserService appUserDetailService;
    private final FileStorageService fileStorageService;
    private final DriverRepository driverRepository;


    /**
     * Validate driver creation given a {@link DriverDTO} object
     *
     * @param driverDTO: a driver DTO object
     */
    private void validateCreation(DriverDTO driverDTO) {
        Optional<User> user = appUserDetailService.findByEmailAndRoleName(driverDTO.getEmail(), UserRole.DRIVER);
        if (user.isPresent()) {
            throw JourneyException.preconditionFailed(String.format("An driver with email : %s already exists", driverDTO.getEmail()));
        }
    }

    /**
     * Create a new driver given a {@link DriverDTO} object
     *
     * @param driverDTO: a driver DTO object
     * @return a {@link Driver} instance
     */
    @Transactional
    public Driver create(DriverDTO driverDTO) {
        validateCreation(driverDTO);
        Optional<Role> role = appUserDetailService.findRoleByName(UserRole.DRIVER);
        if (!role.isPresent()) {
            throw JourneyException.preconditionFailed(String.format("Role with name: %s doesn't exists", UserRole.DRIVER.name()));
        }
        Driver driver = DriverMapping.INSTANCE.driverDTOtoDriver(driverDTO);
        User user = driver.getUser();
        user.setRole(role.get());
        User createdUser = appUserDetailService.create(user);
        driver.setUser(createdUser);
        Path path = fileStorageService.resolveFilePath(DriverConstants.DRIVER_IMAGE_PATH, driverDTO.getImageFile().getOriginalFilename());
        path = fileStorageService.saveFile(driverDTO.getImageFile(), path);
        driver.setImage(path.getFileName().toString());
        return driverRepository.save(driver);
    }

}
