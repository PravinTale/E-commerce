package com.newproject.ecomm.service;

import com.newproject.ecomm.exceptions.PasswordMismatchException;
import com.newproject.ecomm.exceptions.UserAlreadyExistsException;
import com.newproject.ecomm.exceptions.UserAlreadyLoggedInException;
import com.newproject.ecomm.exceptions.UserNotFoundException;
import com.newproject.ecomm.model.CurrentUserSession;
import com.newproject.ecomm.model.User;
import com.newproject.ecomm.model.UserDTO;
import com.newproject.ecomm.repository.CurrentUserSessionDao;
import com.newproject.ecomm.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;
import net.bytebuddy.utility.RandomString;

public class UserLoginImpl implements UserLogin{

    @Autowired
    private CurrentUserSessionDao currentUserRepo;
    @Autowired
    private UserDao userRepo;
    @Override
    public String userLogin(UserDTO userDTO) {

        User user = userRepo.findByMobile(userDTO.getMobile());

        if(user==null) throw new UserNotFoundException("User not found for mobile number:" + userDTO.getMobile());
        Optional<CurrentUserSession> userById = currentUserRepo.findById(user.getUserId());

        if(userById.isPresent()) throw  new UserAlreadyLoggedInException("User already logged in");

        if(user.getPassword().equals(userDTO.getPassword())){
            String key = RandomString.make(6);
            CurrentUserSession currentUserSession = new CurrentUserSession(user.getUserId(), key, LocalDateTime.now());
            currentUserRepo.save(currentUserSession);
            return currentUserSession.toString();
        }
        else {
            throw new PasswordMismatchException("Password does not match");
        }


    }
}
