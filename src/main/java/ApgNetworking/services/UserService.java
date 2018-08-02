package ApgNetworking.services;


import ApgNetworking.models.ApgUser;
import ApgNetworking.repositories.RoleRepository;
import ApgNetworking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public ApgUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Long countByEmail(String email) {
        return userRepository.countByEmail(email);
    }
    public ApgUser findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public void saveUser(ApgUser apgUser) {
        apgUser.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
        apgUser.setEnabled(true);
        userRepository.save(apgUser);
    }
    public void saveAdmin(ApgUser user) {
        user.setRoles(Arrays.asList(roleRepository.findByRole("ADMIN")));
        user.setEnabled(true);
        userRepository.save(user);
    }
}