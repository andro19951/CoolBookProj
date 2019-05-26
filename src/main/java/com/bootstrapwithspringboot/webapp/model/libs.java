package com.bootstrapwithspringboot.webapp.model;


import javax.persistence.*;
import java.util.Collections;

@Entity
@Table(name = "libs")
public class libs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer UserId;

    private Integer BookId;

    public libs() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public Integer getBookId() {
        return BookId;
    }

    public void setBookId(Integer bookId) {
        BookId = bookId;
    }
}

