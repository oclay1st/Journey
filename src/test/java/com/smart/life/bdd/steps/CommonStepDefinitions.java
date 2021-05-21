package com.smart.life.bdd.steps;

import java.util.Optional;

import com.smart.life.kernel.JourneyException;
import com.smart.life.saas.domain.core.user.Role;
import com.smart.life.saas.domain.core.user.RoleRepository;
import com.smart.life.saas.domain.core.user.User;
import com.smart.life.saas.domain.core.user.UserRole;
import com.smart.life.saas.domain.core.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;


public class CommonStepDefinitions {

    @Autowired
    UserService userService;

    RoleRepository roleRepository;
        
    @Given("^Jhon is an admin user$")
    public void jhonIsAnAdminUser(){
        Optional<Role> role = roleRepository.findByName(UserRole.ADMIN);
        if(!role.isPresent()){
            throw JourneyException.notFound("Role with name "+ UserRole.ADMIN + " was not fount");
        }
        User user = new User();
        user.setName("Jhon");
        user.setActive(true);
        user.setRole(role.get());
        user.setEmail("jhon@admin.com");
        user.setPassword("johnnycage");
        userService.create(user);
    }

}
