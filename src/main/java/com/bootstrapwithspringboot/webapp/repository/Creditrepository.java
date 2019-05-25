package com.bootstrapwithspringboot.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bootstrapwithspringboot.webapp.model.credit;

@Repository
public interface Creditrepository extends CrudRepository<credit, Integer> {

}