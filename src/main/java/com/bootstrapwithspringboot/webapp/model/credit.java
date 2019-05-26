package com.bootstrapwithspringboot.webapp.model;
import javax.persistence.*;


@Entity
@Table(name = "credit")
public class credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String Isvalid;

    public credit() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsvalid() {
        return Isvalid;
    }

    public void setIsvalid(String isvalid) {
        Isvalid = isvalid;
    }
}
