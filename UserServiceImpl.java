package com.MainBus.implementation;



import com.MainBus.Model.User;
import com.MainBus.Repository.UserRepository;
import com.MainBus.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean validateUser(String phoneNumber, String password) {
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
        return userOptional.map(user -> user.getPassword().equals(password)).orElse(false);
    }
}
