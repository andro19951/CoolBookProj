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
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/")
public class WebAppContoller {
    private String appMode;

    @Autowired
    public WebAppContoller(Environment environment) {
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
    public String index(Model model) {


        String username = currentuser.getUser_name();
        model.addAttribute("datetime", new Date());
        if (username != null) {
            model.addAttribute("username", username);
        }
        model.addAttribute("dude", currentuser);
        model.addAttribute("projectname", "WebApp");
        model.addAttribute("books", bookrepository.findAll());
        model.addAttribute("mode", appMode);


        return "index";
    }

    @RequestMapping("/admin/addbook")
    public String adminpage(Model model) {
        model.addAttribute("dude", currentuser);
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "@omeryazir");
        model.addAttribute("projectname", "WebApp");

        model.addAttribute("mode", appMode);

        return "admin";

    }

    @RequestMapping("/Register")
    public String register(Model model) {
        String username = currentuser.getUser_name();

        if (username != null) {
            model.addAttribute("username", username);
        }
        model.addAttribute("dude", currentuser);

        model.addAttribute("mode", appMode);


        return "register";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        String username = currentuser.getUser_name();

        if (username != null) {
            model.addAttribute("username", username);
        }
        model.addAttribute("dude", currentuser);

        model.addAttribute("mode", appMode);


        return "login";
    }

    @RequestMapping(value = "/logedin", method = {RequestMethod.GET, RequestMethod.POST})
    public String logedin(Model model, @RequestParam String Name,
                          @RequestParam String Passwd,
                          @RequestParam String Email) {
        currentuser = (users) usersrepository.findByEmail(Email);
        String username = currentuser.getUser_name();

        if (username != null) {
            model.addAttribute("username", username);
        }
        model.addAttribute("mode", appMode);

        model.addAttribute("dude", currentuser);

        model.addAttribute("datetime", new Date());
        model.addAttribute("username", username);
        model.addAttribute("projectname", "WebApp");
        model.addAttribute("books", bookrepository.findAll());
        model.addAttribute("mode", appMode);
        if (currentuser.getUser_name() == null) {
            return "login";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/admin/bookadded", method = {RequestMethod.GET, RequestMethod.POST})
    public String addNewBook(Model model, @RequestParam String bName,
                             @RequestParam String bCover,
                             @RequestParam String bLink,
                             @RequestParam String bDescription,
                             @RequestParam Integer bPrice
    ) {
        books n = new books();
        n.setbName(bName);
        n.setbCover(bCover);
        n.setbLink(bLink);
        n.setbDescription(bDescription);
        n.setbPrice(bPrice);
        bookrepository.save(n);
        String username = currentuser.getUser_name();

        if (username != null) {
            model.addAttribute("username", username);
        }
        model.addAttribute("mode", appMode);

        model.addAttribute("dude", currentuser);

        model.addAttribute("datetime", new Date());
        model.addAttribute("username", username);
        model.addAttribute("projectname", "WebApp");
        model.addAttribute("books", bookrepository.findAll());
        model.addAttribute("mode", appMode);
        return "index";
    }

    @RequestMapping(value = "/is/registered", method = {RequestMethod.GET, RequestMethod.POST})
    public String addNewUser(Model model, @RequestParam String Name,
                             @RequestParam String Passwd,
                             @RequestParam String Passwd2,
                             @RequestParam String Email) {
        Date date = new Date();
        String newdate = date.toString();
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
        String username = currentuser.getUser_name();

        if (username != null) {
            model.addAttribute("username", username);
        }
        model.addAttribute("mode", appMode);

        model.addAttribute("dude", currentuser);

        model.addAttribute("datetime", new Date());
        model.addAttribute("username", username);
        model.addAttribute("projectname", "WebApp");
        model.addAttribute("books", bookrepository.findAll());
        model.addAttribute("mode", appMode);


        return "index";

    }

    @RequestMapping(value = "/admin/users", method = {RequestMethod.POST, RequestMethod.GET})
    public String getAllUsers(Model model) {
        String username = currentuser.getUser_name();

        if (username != null) {
            model.addAttribute("username", username);
        }
        model.addAttribute("mode", appMode);
        model.addAttribute("dude", currentuser);
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", username);
        model.addAttribute("dudes", usersrepository.findAll());
        model.addAttribute("mode", appMode);

        return "users";

    }

    @RequestMapping(value = "/admin/users", params = "demote", method = {RequestMethod.POST, RequestMethod.GET})
    public String demoteuser(Model model, @RequestParam String email) {
        users user1 = usersrepository.findByEmail(email);
        user1.setAdmin_not(0);
        usersrepository.save(user1);
        String username = currentuser.getUser_name();

        if (username != null) {
            model.addAttribute("username", username);
        }
        model.addAttribute("mode", appMode);
        model.addAttribute("dude", currentuser);
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", username);
        model.addAttribute("dudes", usersrepository.findAll());
        model.addAttribute("mode", appMode);

        return "users";

    }

    @RequestMapping(value = "/admin/users", params = "promote", method = {RequestMethod.POST, RequestMethod.GET})
    public String promoteuser(Model model, @RequestParam String email) {

        users user1 = usersrepository.findByEmail(email);
        user1.setAdmin_not(1);
        usersrepository.save(user1);

        String username = currentuser.getUser_name();

        if (username != null) {
            model.addAttribute("username", username);
        }
        model.addAttribute("mode", appMode);

        model.addAttribute("dude", currentuser);

        model.addAttribute("datetime", new Date());
        model.addAttribute("username", username);
        model.addAttribute("dudes", usersrepository.findAll());
        model.addAttribute("mode", appMode);

        return "users";

    }
    @RequestMapping(value = "/admin/users", params = "delete", method = {RequestMethod.POST, RequestMethod.GET})
    public String deleteuser(Model model, @RequestParam String email) {

        users user1 = usersrepository.findByEmail(email);
        usersrepository.delete(user1);

        String username = currentuser.getUser_name();

        if (username != null) {
            model.addAttribute("username", username);
        }
        model.addAttribute("mode", appMode);

        model.addAttribute("dude", currentuser);

        model.addAttribute("datetime", new Date());
        model.addAttribute("username", username);
        model.addAttribute("dudes", usersrepository.findAll());
        model.addAttribute("mode", appMode);

        return "users";

    }
    @RequestMapping(value = "/addCash", method = {RequestMethod.POST, RequestMethod.GET})
    public String getcash(Model model) {
        String username = currentuser.getUser_name();


        if (username != null) {
            model.addAttribute("username", username);
        }

        model.addAttribute("books", bookrepository.findAll());
        model.addAttribute("mode", appMode);
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", username);
        model.addAttribute("dude", currentuser);
        model.addAttribute("mode", appMode);

        return "addcash";

    }

    @RequestMapping(value = "/addCash", params = "addcash", method = {RequestMethod.POST, RequestMethod.GET})
    public String getcash1(Model model, @RequestParam Integer credit) {
        String username = currentuser.getUser_name();

        users user = (users) usersrepository.findByEmail(currentuser.getEmail());
        user.setCredit(credit + currentuser.getCredit());
        usersrepository.save(user);

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


        return "addcash";

    }

    @RequestMapping(value = "/books", method = {RequestMethod.GET, RequestMethod.POST})
    public String allbooks(Model model) {
        String username = currentuser.getUser_name();
        if (username != null) {
            model.addAttribute("username", username);
        }

        model.addAttribute("books", bookrepository.findAll());
        model.addAttribute("mode", appMode);
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", username);
        model.addAttribute("dude", currentuser);
        model.addAttribute("mode", appMode);

        return "books";

    }

    @RequestMapping(value = "/books", params = "buy", method = {RequestMethod.GET, RequestMethod.POST})
    public String buybook(Model model, @RequestParam String getbName) {

        String username = currentuser.getUser_name();

        books book1 = (books) bookrepository.findByBName(getbName);
        if (book1.getbPrice() < currentuser.getCredit()) {
            currentuser.setCredit(currentuser.getCredit() - book1.getbPrice());
            libs lib = new libs();
            lib.setBookId(book1.getId());
            lib.setUserId(currentuser.getId());
            libsrepository.save(lib);
            users user = (users) usersrepository.findByEmail(currentuser.getEmail());
            user.setCredit(currentuser.getCredit());
            usersrepository.save(user);

        }

        if (username != null) {
            model.addAttribute("username", username);
        }

        model.addAttribute("books", bookrepository.findAll());
        model.addAttribute("mode", appMode);
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", username);
        model.addAttribute("dude", currentuser);
        model.addAttribute("mode", appMode);

        return "books";

    }

    @RequestMapping(value = "/yourbooks", method = {RequestMethod.GET, RequestMethod.POST})
    public String yourbooks(Model model) {

        List<books> mybooks = new ArrayList<books>();
        List<libs> liblist = (List<libs>) libsrepository.findAll();
        for (libs li: liblist ) {
            if(currentuser.getId()==li.getUserId()) {
                books book = bookrepository.findFirstById(li.getId());
                mybooks.add(book);
            }
        }
        model.addAttribute("dude", currentuser);
        model.addAttribute("mybooks", mybooks);
        model.addAttribute("mode", appMode);

        return "yourlib";
    }

}