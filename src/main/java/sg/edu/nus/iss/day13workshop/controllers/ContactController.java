package sg.edu.nus.iss.day13workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.day13workshop.models.Contact;
import sg.edu.nus.iss.day13workshop.services.DatabaseService;

@Controller
@RequestMapping(path = "/contact")
public class ContactController {
    
    //must use autowired to make sure the controller can use the service class
    @Autowired
    private DatabaseService dbSvc;

    //consumes data from form encoded data in the localhost:8085/AddContact page and
    // then produces a text or html file
    @PostMapping(consumes = "application/x-www-form-urlencoded", produces = "text/html")
    //a method to serve our creation
    //RequestBody will read the data AKA the form content
    public String postContact(@RequestBody MultiValueMap<String, String> form, Model model) {
        
        Contact c = new Contact();
        c.setName(form.getFirst("name"));
        c.setEmail(form.getFirst("email"));
        c.setPhone(form.getFirst("phone"));

        //System.out.printf("Contact %s", c);
        dbSvc.save(c);

        model.addAttribute("contact", c);

        return "showContact";
    }


    //GetMapping vs PostMapping, data is read from the file, injected into a model
    @GetMapping(value="/{id}", produces = "text/html")
    public String getContact (@PathVariable("id") String id, Model model) {

        Contact c = new Contact();

        c = dbSvc.read(id);
        System.out.printf("> id: %s",c);

        model.addAttribute("contact", c);
        return "showContact";
    }
}
