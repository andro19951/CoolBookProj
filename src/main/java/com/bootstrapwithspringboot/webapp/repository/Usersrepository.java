package com.bootstrapwithspringboot.webapp.repository;

import com.bootstrapwithspringboot.webapp.model.users;
import org.springframework.data.repository.CrudRepository;



public interface Usersrepository extends CrudRepository<users, Integer> {


    users findByEmail(String email);

}

