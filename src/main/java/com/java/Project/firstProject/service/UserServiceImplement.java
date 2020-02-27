package com.java.Project.firstProject.service;

import com.java.Project.firstProject.entity.User;
import com.java.Project.firstProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getUserData() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> updateUserData(Long id, User user) {

        return userRepo.findById(id).map(user1 -> {
                    user1.setFirstName(user.getFirstName());
                    user1.setLastName(user.getLastName());
                    user1.setFeedback(user.getFeedback());
                    user1.setEmailAddress(user.getEmailAddress());
                    return userRepo.save(user1);
                }
        );


    }

    @Override
    public Optional<?> deleteByUsrId(Long id) {
        return userRepo.findById(id).map(user -> {
            userRepo.delete(user);
            return ResponseEntity.ok().body("Deleted Successfully" + id);
        });
    }

    @Override
    public Optional<User> findByID(Long id) {
        return userRepo.findById(id);
    }
}

