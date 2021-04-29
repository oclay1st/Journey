package com.smart.life.saas.domain.core.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import com.smart.life.kernel.JourneyException;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User : " + email + " not found"));
    }

    /**
     * Find user given an email and a role
     *
     * @param email: a given email
     * @param userRole: a given role type
     * @return {@link User}: an user entity object
     */
    public Optional<User> findByEmailAndRoleName(String email, UserRole userRole){
        return userRepository.findByEmailAndRoleName(email, userRole);
    }


    /**
     * Find {@link Role} by name
     *
     * @param userRole: a role name
     * @return Role: a role
     */
    public Optional<Role> findRoleByName(UserRole userRole){
        return roleRepository.findByName(userRole);
    }

    /**
     * Create new user
     *
     * @param user: a given {@link User}
     * @return User: an user object
     */
    @Transactional
    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }

    /**
     * Find {@link User} given the email and password
     * @param email : an user email 
     * @param password: a user password
     * @return User: an user object
     */
    public User findUserByEmailAndPassword(String email, String password){
        Optional<User> user =  userRepository.findByEmailAndPassword(email, password);
        if(!user.isPresent()){
            throw JourneyException.notFound("Invalid email or password");
        }
        return user.get();
    }
}
