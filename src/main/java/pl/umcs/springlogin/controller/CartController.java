package pl.umcs.springlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.umcs.springlogin.data.Cart;
import pl.umcs.springlogin.service.CartService;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @GetMapping("/add/{bookId}/{quantity}")
    public String addToCart(@PathVariable int bookId, @PathVariable int quantity) {
        cartService.addToCart(bookId, quantity);
        return "redirect:/cart";
    }

    @GetMapping
    public String getCart(Model model) {
        Cart cart = cartService.getCart();
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/remove/{bookId}/{quantity}")
    public String removeFromCart(@PathVariable int bookId, @PathVariable int quantity) {
        cartService.removeFromCart(bookId, quantity);
        return "redirect:/cart";
    }

}
