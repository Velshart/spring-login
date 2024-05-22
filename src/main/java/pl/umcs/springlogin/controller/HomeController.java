package pl.umcs.springlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.umcs.springlogin.service.BookService;

@Controller
public class HomeController {

    @Autowired
    private BookService bookService;

    @GetMapping({"/home", "/"})
    public String home(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "home";
    }

    @RequestMapping(path = {"/main", "/", "/index"}, method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("books", this.bookService.getAll());
        return "books";
    }

    @RequestMapping(path = "/guestbooks", method = RequestMethod.GET)
    public String guestbooks(Model model) {
        model.addAttribute("books", this.bookService.getAll());
        return "books-for-guests";
    }

}
