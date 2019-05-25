package com.bootstrapwithspringboot.webapp.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String user_name;

    private String email;

    private Integer admin_not;

    private String passd;

    private Integer credit;

    private Integer valid_card;

    private String reg_date;

    public users(){
        super();
    }

    public users(String user_name, String email, Integer admin_not, String passd, Integer credit, Integer valid_card, String reg_date) {
        this.user_name = user_name;
        this.email = email;
        this.admin_not = admin_not;
        this.passd = passd;
        this.credit = credit;
        this.valid_card = valid_card;
        this.reg_date = reg_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAdmin_not() {
        return admin_not;
    }

    public void setAdmin_not(Integer admin_not) {
        this.admin_not = admin_not;
    }

    public String getPassd() {
        return passd;
    }

    public void setPassd(String passd) {
        this.passd = passd;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getValid_card() {
        return valid_card;
    }

    public void setValid_card(Integer valid_card) {
        this.valid_card = valid_card;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }


}
