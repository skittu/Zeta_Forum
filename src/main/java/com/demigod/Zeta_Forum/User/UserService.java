package com.demigod.Zeta_Forum.User;

import com.demigod.Zeta_Forum.Question.ReturnQuestion;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserReturn createAccount(Theuser user)
    {
    System.out.println(user.getUserName());
    user.setUserId(UUID.randomUUID().toString());
    UserReturn u=new UserReturn();
    try {
        userRepository.save(user);
    }
    catch(DataIntegrityViolationException e) {
        System.out.println(e);

        u.setMsg("User already exist");
        return u;
    }
    catch (ConstraintViolationException e)
    {
        System.out.println(e);
        u.setMsg("User already exist");
        return u;
    }
    catch (Exception e )
    {
        System.out.println("some error occurred");
        u.setMsg("some error occurred");
        return u;
    }
    UserReturn createdUser=new UserReturn(userRepository.findById(user.getUserId()).get());
    createdUser.setMsg("1");
    createdUser.setPassword("");
        return createdUser;
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

    public UserReturn Login(Theuser user) {
        Theuser u;
        UserReturn r =new UserReturn();
        try
        {

            if(user.getUserName()!=null && user.getUserName().length()>0)
            u = userRepository.findByUserName(user.getUserName());
            else{
                u=userRepository.findByEmail(user.getEmail());
            }
            if(u.getPassword().equals(user.getPassword()))
            {
                UserReturn ru= new UserReturn(u);
                ru.setPassword("");
                ru.setMsg("1");
                return ru;
            }
        }
        catch (Exception e)
        {
            r.setMsg("User does not exist, Please Sign up first");
            return r;
        }
        r.setMsg("Password is wrong");
        return r;
    }
}
