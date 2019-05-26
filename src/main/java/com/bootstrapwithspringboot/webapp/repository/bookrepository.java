package com.bootstrapwithspringboot.webapp.repository;

import com.bootstrapwithspringboot.webapp.model.books;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface bookrepository extends CrudRepository<books, Integer> {

    books findByBName(String bname);

books findFirstById(Integer id);
}
