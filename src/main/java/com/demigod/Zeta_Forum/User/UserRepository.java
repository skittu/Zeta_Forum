package com.demigod.Zeta_Forum.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Theuser,String> {

//    @Query(nativeQuery = true,value = "select user_id,user_name from user where username = ?1")
//    Theuser findByUserName(String username);
//
//    @Query(nativeQuery = true,value = "select user_id,user_name from user where email = ?1")
//    Theuser findByEmail(String email);

    
    Theuser findByUserName(String username);


    Theuser findByEmail(String email);
}
