package com.demigod.Zeta_Forum.User;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public String createAccount(Theuser user)
    {

    user.setUserId(UUID.randomUUID().toString());

    try {
        userRepository.save(user);
    }
    catch(DataIntegrityViolationException e) {
        System.out.println(e);
        return "Username or email already exists";
    }
    catch (ConstraintViolationException e)
    {
        System.out.println(e);
        return "Username or email already exist";
    }
    catch (Exception e )
    {
        System.out.println("some error occurred");
        return "some error occurred";
    }
        return "User Created Successfully";
    }


    public String validateUserName(String username)
    {
        try {
            Theuser s=userRepository.findByUserName(username);
            System.out.println(s.getUserId());
        }
        catch (Exception e)
        {
            return "1";
        }
        return "0";
    }

    public String validateEmail(String email)
    {
        try {
            Theuser s=userRepository.findByEmail(email);
            System.out.println(s.getUserId());
        }
        catch (Exception e)
        {
            return "1";
        }
        return "0";
    }


    public String deleteAccount(String userId,String password) {

        Theuser u;
        try {
            u=userRepository.findById(userId).get();

        }
        catch (Exception e)
        {
            return "User does not exist";
        }

        try{

            if(u.getPassword().equals(password))
            {
                userRepository.deleteById(userId);
            }
        }
        catch (Exception e)
        {
            return "Some error occurred in deleted account";
        }
        return  "Account deleted successfully";
    }

    public String forgotPassword(Theuser user) {

        return "abcd";

    }
}
