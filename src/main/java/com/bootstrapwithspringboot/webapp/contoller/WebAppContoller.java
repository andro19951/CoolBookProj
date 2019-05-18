package com.bootstrapwithspringboot.webapp.contoller;
import com.bootstrapwithspringboot.webapp.model.books;
import com.bootstrapwithspringboot.webapp.repository.bookrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;

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

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "@omeryazir");
        model.addAttribute("projectname", "WebApp");

        model.addAttribute("mode", appMode);

        return "index";
    }
    @RequestMapping("/admin")
    public String adminpage(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "@omeryazir");
        model.addAttribute("projectname", "WebApp");

        model.addAttribute("mode", appMode);

        return "admin";
    }
    @GetMapping("/add")
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
   @GetMapping(path="/all")
    public @ResponseBody Iterable<books> getAllUsers() {
        return bookrepository.findAll();

    }
}
