package com.bootstrapwithspringboot.webapp.contoller;
import com.bootstrapwithspringboot.webapp.model.books;
import com.bootstrapwithspringboot.webapp.model.users;
import com.bootstrapwithspringboot.webapp.model.credit;
import com.bootstrapwithspringboot.webapp.repository.Creditrepository;
import com.bootstrapwithspringboot.webapp.repository.bookrepository;
import com.bootstrapwithspringboot.webapp.repository.Usersrepository;
import com.bootstrapwithspringboot.webapp.repository.libsrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
public class WebAppContoller {
    private String appMode;

    @Autowired
    public WebAppContoller(Environment environment){
        appMode = environment.getProperty("app-mode");
    }

    @Autowired
    private bookrepository bookrepository;

    @Autowired
    private Usersrepository usersrepository;

    @Autowired
    private libsrepository libsrepository;


    private users currentuser = new users();

    @RequestMapping("/")
    public String index(Model model){


        String username= currentuser.getUser_name();
        model.addAttribute("datetime", new Date());
        if(username!=null) {
            model.addAttribute("username", username);
        }
        model.addAttribute("projectname", "WebApp");
        model.addAttribute("books",bookrepository.findAll());
        model.addAttribute("mode", appMode);



        return "index";
    }
    @RequestMapping("/admin")
    public String adminpage(Model model){
        if(currentuser.getAdmin_not()==1) {
            model.addAttribute("datetime", new Date());
            model.addAttribute("username", "@omeryazir");
            model.addAttribute("projectname", "WebApp");

            model.addAttribute("mode", appMode);

            return "admin";
        }
        else{
            return "forbiden";
        }
    }
    @RequestMapping("/Register")
    public String register(Model model){
        String username= currentuser.getUser_name();

        if(username!=null) {
            model.addAttribute("username", username);
        }
        model.addAttribute("mode", appMode);


        return "register";
    }
    @RequestMapping("/login")
    public String login(Model model){
        String username= currentuser.getUser_name();

        if(username!=null) {
            model.addAttribute("username", username);
        }
        model.addAttribute("mode", appMode);


        return "login";
    }
    @GetMapping("/logedin")
    public String logedin(@RequestParam String Name,
                          @RequestParam String Passwd,
                          @RequestParam String Email){
        currentuser= (users) usersrepository.findByEmail(Email);
        if(currentuser.getUser_name()==null){
            return "main";
        }
        else {
            return "index";
        }
    }
    @GetMapping("/admin/addbook")
    public @ResponseBody String addNewBook (@RequestParam String bName,
                                            @RequestParam String bCover,
                                            @RequestParam String bLink,
                                            @RequestParam String bDescription,
                                            @RequestParam Integer bPrice
    ){
        books n = new books();
        n.setbName(bName);
        n.setbCover(bCover);
        n.setbLink(bLink);
        n.setbDescription(bDescription);
        n.setbPrice(bPrice);
        bookrepository.save(n);
        return "Saved";
    }
    @GetMapping("/is/registered")
    public @ResponseBody String addNewUser(@RequestParam String Name,
                                           @RequestParam String Passwd,
                                           @RequestParam String Passwd2,
                                           @RequestParam String Email){
        Date date = new Date();
        String newdate= date.toString();
        users n = new users();
        n.setAdmin_not(0);
        n.setCredit(0);
        n.setValid_card(0);
        n.setEmail(Email);
        n.setUser_name(Name);
        n.setPassd(Passwd);
        n.setReg_date(newdate);
        usersrepository.save(n);

        currentuser = n;
        return "index";

    }

    @RequestMapping("/all")
    public  String getAllUsers(Model model) {
        String username= currentuser.getUser_name();

        if(username!=null) {
            model.addAttribute("username", username);
        }
        model.addAttribute("mode", appMode);


        model.addAttribute("datetime", new Date());
        model.addAttribute("username", username);
        model.addAttribute("projectname", "WebApp");
        model.addAttribute("books",bookrepository.findAll());
        model.addAttribute("mode", appMode);

        return "index";

    }
    @RequestMapping(value = "/addCash",  method ={ RequestMethod.POST, RequestMethod.GET})
    public  String getcash(Model model) {
        String username= currentuser.getUser_name();

        //users user = (users) usersrepository.findByEmail(currentuser.getEmail());
        //user.setCredit(credit);
        //usersrepository.save(user);

        if(username!=null) {
            model.addAttribute("username", username);
        }

        model.addAttribute("books",bookrepository.findAll());
        model.addAttribute("mode", appMode);


        model.addAttribute("datetime", new Date());
        model.addAttribute("username", username);
        model.addAttribute("dude", currentuser);
        model.addAttribute("mode", appMode);

        return "addcash";

    }
    @RequestMapping(value = "/addCash", params= "addcash" ,  method ={ RequestMethod.POST, RequestMethod.GET})
    public  String getcash1(Model model,@RequestParam Integer credit) {
        String username= currentuser.getUser_name();

        users user = (users) usersrepository.findByEmail(currentuser.getEmail());
        user.setCredit(credit+currentuser.getCredit());
        usersrepository.save(user);

        if(username!=null) {
            model.addAttribute("username", username);
        }

        model.addAttribute("books",bookrepository.findAll());
        model.addAttribute("mode", appMode);

        currentuser = user;
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", username);
        model.addAttribute("dude", currentuser);
        model.addAttribute("mode", appMode);


        return "addcash";

    }
}