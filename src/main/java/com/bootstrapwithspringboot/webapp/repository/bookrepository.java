package com.bootstrapwithspringboot.webapp.repository;

import com.bootstrapwithspringboot.webapp.model.books;

import org.springframework.data.repository.CrudRepository;


public interface bookrepository extends CrudRepository<books, Integer> {


}
