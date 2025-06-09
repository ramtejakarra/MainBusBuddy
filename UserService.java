package com.MainBus.Service;



import com.MainBus.Model.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> findByPhoneNumber(String phoneNumber);
    boolean validateUser(String phoneNumber, String password);
}

