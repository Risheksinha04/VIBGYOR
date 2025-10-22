package org.project.vibgyor.service;

import org.apache.catalina.User;
import org.project.vibgyor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllActiveUsers() {
        return userRepository.findByStatusTrue();
    }

    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus(true);
        return userRepository.save(user);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUser(Integer id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found");
    }

    public void deactivateUser(Integer id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setStatus(false);
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
        });
    }
}
