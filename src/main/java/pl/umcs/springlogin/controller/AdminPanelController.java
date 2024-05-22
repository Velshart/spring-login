package pl.umcs.springlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.umcs.springlogin.service.BookService;

@Controller
public class AdminPanelController {

    @Autowired
    private BookService bookService;

    @GetMapping("/admin/adminpanel")
    public String adminpanel(Model model) {
        model.addAttribute("books", this.bookService.getAll());
        return "adminpanel";
    }
}
