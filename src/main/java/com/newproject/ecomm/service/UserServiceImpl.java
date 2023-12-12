package com.newproject.ecomm.service;

import com.newproject.ecomm.exceptions.DuplicateMobileNumberException;
import com.newproject.ecomm.exceptions.UserAlreadyExistsException;
import com.newproject.ecomm.exceptions.UserNotFoundException;
import com.newproject.ecomm.exceptions.UserNotLoggedInException;
import com.newproject.ecomm.model.CurrentUserSession;
import com.newproject.ecomm.model.User;
import com.newproject.ecomm.repository.CurrentUserSessionDao;
import com.newproject.ecomm.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Currency;
import java.util.Optional;

public class UserServiceImpl implements  UserService{
    @Autowired
    public UserDao userRepo;
    @Autowired
    public CurrentUserSessionDao currentUserRepo;
    @Override
    public User saveUser(User user) {
        User userPresent = userRepo.findByMobile(user.getMobile());
        if(userPresent == null) return userRepo.save(user);
        else throw new UserAlreadyExistsException("User already exists ");
    }

    @Override
    public User updateUserCreds(User user, String key) {
        // Retrieve current user session
        CurrentUserSession currentUser = currentUserRepo.findByUniqueId(key);
        if (currentUser==null) throw new UserNotLoggedInException("User not logged in for key:"+ key);
        // Retrieve user by ID from the session
        Optional<User> userOptional = userRepo.findById(currentUser.getUserId());
        if(userOptional.isEmpty()) throw new UserNotFoundException("User not found for Id: "+currentUser.getUserId());

        // Check for duplicate mobile number
        User userWithSameNumber = userRepo.findByMobile(user.getMobile());
        if (userWithSameNumber != null && !userWithSameNumber.getUserId().equals(userOptional.get().getUserId())) {
            throw new DuplicateMobileNumberException("Another user with the same mobile number already exists.");
        }
        // Update user
        User updatedUser = userRepo.save(user);

        // Delete current user session and original user
        currentUserRepo.delete(currentUser);
        userRepo.delete(userOptional.get());

        return updatedUser;

    }

    @Override
    public String userLogOut(String key) {
        CurrentUserSession currentUserSession = currentUserRepo.findByUniqueId(key);
        if (currentUserSession == null) throw  new UserNotLoggedInException("User not logged in for key:");
        currentUserRepo.delete(currentUserSession);
        return "Logged out successfully";

    }
}
