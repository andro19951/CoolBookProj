package com.bootstrapwithspringboot.webapp.model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String bName;

    private String bCover;

    private String bLink;

    private String bDescription;

    private Integer bPrice;

    public books() {
         super();
    }

    public books(String bName, String bCover, String bLink, String bDescription, Integer bPrice) {
       super();
        this.bName = bName;
        this.bCover = bCover;
        this.bLink = bLink;
        this.bDescription = bDescription;
        this.bPrice = bPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbCover() {
        return bCover;
    }

    public void setbCover(String bCover) {
        this.bCover = bCover;
    }

    public String getbLink() {
        return bLink;
    }

    public void setbLink(String bLink) {
        this.bLink = bLink;
    }

    public String getbDescription() {
        return bDescription;
    }

    public void setbDescription(String bDescription) {
        this.bDescription = bDescription;
    }

    public Integer getbPrice() {
        return bPrice;
    }

    public void setbPrice(Integer bPrice) {
        this.bPrice = bPrice;
    }

    public void putbooks(books book){

    }
}
