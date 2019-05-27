package com.bootstrapwithspringboot.webapp.contoller;
import com.bootstrapwithspringboot.webapp.model.books;
import com.bootstrapwithspringboot.webapp.model.users;
import com.bootstrapwithspringboot.webapp.model.libs;
import com.bootstrapwithspringboot.webapp.repository.bookrepository;
import com.bootstrapwithspringboot.webapp.repository.Usersrepository;
import com.bootstrapwithspringboot.webapp.repository.libsrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Controller
@RequestMapping("/addcash")
public class WebAppController2 {
    private String appMode;

    @Autowired
    public WebAppController2(Environment environment) {
        appMode = environment.getProperty("app-mode");
    }

    @Autowired
    private bookrepository bookrepository;

    @Autowired
    private Usersrepository usersrepository;

    @Autowired
    private libsrepository libsrepository;

    private void SendInfoToHtml(Model model, String username,users user) {
        senddata(model, username, user);
    }
    private void GetCashData(Model model, String username, users user) {
        senddata(model, username, user);
    }

    private void senddata(Model model, String username, users user) {
        if (username != null) {
            model.addAttribute("username", username);
        }

        model.addAttribute("books", bookrepository.findAll());
        model.addAttribute("mode", appMode);
        currentuser = user;
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", username);
        model.addAttribute("dude", currentuser);
        model.addAttribute("mode", appMode);
    }

    private users currentuser = new users();
    @RequestMapping(value = "/cash", method = {RequestMethod.POST, RequestMethod.GET})
    public String getcash(Model model) {
        String username = currentuser.getUser_name();
        users user = (users) usersrepository.findByEmail(currentuser.getEmail());


        SendInfoToHtml(model, username, user);

        return "addcash";

    }

    @RequestMapping(value = "/cash", params = "addcash", method = {RequestMethod.POST, RequestMethod.GET})
    public String getcash1(Model model, @RequestParam Integer credit) {
        String username = currentuser.getUser_name();

        users user = (users) usersrepository.findByEmail(currentuser.getEmail());
        user.setCredit(credit + currentuser.getCredit());
        usersrepository.save(user);

        GetCashData(model, username, user);


        return "addcash";

    }




}
