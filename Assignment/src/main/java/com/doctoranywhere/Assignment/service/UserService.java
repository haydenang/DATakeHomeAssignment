package com.doctoranywhere.Assignment.service;


import com.doctoranywhere.Assignment.model.User;
import com.doctoranywhere.Assignment.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepo userRepo;
    @Autowired
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public Optional<User> getUserById(long id){
        return userRepo.findById(id);
    }

    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    public User uploadUser(User user){
        return userRepo.save(user);
    }

    public User updateUser(long id, User user){
        Optional<User> retrievedUser = userRepo.findById(id);
        if(retrievedUser.isPresent()){
            User toBeUpdatedUser = retrievedUser.get();
            toBeUpdatedUser.setEmail(user.getEmail());
            toBeUpdatedUser.setName(user.getName());
            toBeUpdatedUser.setPassword(user.getPassword());
            toBeUpdatedUser.setRole(user.getRole());
            return userRepo.save(toBeUpdatedUser);
        }
        else{
            return uploadUser(user);
        }
    }

    public void deleteTask(long id){
        userRepo.deleteById(id);
    }
}
