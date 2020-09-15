package com.example.infrastructureForProjects.Service;

import com.example.infrastructureForProjects.Repository.UserRepository;
import com.example.infrastructureForProjects.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.*;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        user.setHashedPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
    }

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    private boolean checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            return true;
        else
            return false;
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /* public String findByUsername(String username) {
        return userRepository.findByUserName(username);
       }
    */

    public boolean findPassword(String password) {
        String hash = hashPassword(password);
        if (userRepository.findHash(hash) != null) {
            return true;
        } else {
            return false;
        }
    }
}


