package com.bootstrapwithspringboot.webapp.repository;

import com.bootstrapwithspringboot.webapp.model.libs;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface libsrepository extends CrudRepository<libs, Integer> {
}
