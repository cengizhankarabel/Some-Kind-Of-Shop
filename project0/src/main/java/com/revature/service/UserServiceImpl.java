package com.revature.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.revature.exceptions.InvalidCredentialException;
import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.User;
import com.revature.repository.UserRepository;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger logger=Logger.getLogger("shoppingCenter");

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerCustomerAccount(User user) {

        // hash the plain-text password
        String bcryptHashString= BCrypt.withDefaults()
                .hashToString(12,user.getLoginPassword().toCharArray());
        user.setLoginPassword(bcryptHashString);

        // save user in database
        userRepository.registerCustomerAccount(user);
    }

    @Override
    public void createEmployeeAccount(User user) {
        String bcryptHashString= BCrypt.withDefaults()
                .hashToString(12,user.getLoginPassword().toCharArray());
        user.setLoginPassword(bcryptHashString);

        // save user in database
        userRepository.createEmployeeAccount(user);
    }

    @Override
    public User loginUser(String email, String password) {

        User user= userRepository.findByEmail(email);
        if(user!=null){
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getLoginPassword());
            if (result.verified){
                return user;
            }else{
                throw new InvalidInputException("password cannot be empty");
            }
        }else {
            throw new UserNotFoundException("User not found!");
        }
    }

    @Override
    public void updateUser() {
        // TODO: 11/5/2021  
    }

    @Override
    public void removeEmployeeAcc(int userId) {
        logger.info("Employee account removing...");
        userRepository.removeEmployeeAcc(userId);
    }

    @Override
    public List<User> employeeAccList(){
        return userRepository.employeeAccList();
    }
}
