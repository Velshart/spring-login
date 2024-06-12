package pl.umcs.springlogin.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.umcs.springlogin.data.Book;
import pl.umcs.springlogin.data.Cart;
import pl.umcs.springlogin.data.User;
import pl.umcs.springlogin.data.interfaces.IBookDAO;
import pl.umcs.springlogin.data.interfaces.UserService;
import pl.umcs.springlogin.repository.CartRepository;


@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private IBookDAO bookRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public Cart getCart() {
        User user = userService.getCurrentUser();
        return user.getCart();
    }

    @Transactional
    public Cart addToCart(int bookId, int quantity) {
        Cart cart = getCart();
        Book book = bookRepository.getById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        cart.addItem(book, quantity);
        return saveCart(cart);
    }

    /*
    @Transactional
    public Cart removeFromCart(int bookId) {
        Cart cart = getCart();
        Book book = bookRepository.getById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));


        List<CartItem> items = cart.getItems();

        int index = findItemIndex(book, items);
        CartItem cartItem = items.get(index);
        if(cartItem.getQuantity() > 1) {

            cartItem.setQuantity(cartItem.getQuantity() - 1);
            items.set(index, cartItem);

            cart.setItems(items);
        }else {
            cart.removeItem(book);
        }
        return saveCart(cart);
    }

     */
    @Transactional
    public Cart removeFromCart(int bookId, int quantity) {
        Cart cart = getCart();
        Book book = bookRepository.getById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        cart.removeItem(book, quantity);
        return saveCart(cart);
    }

    @Transactional
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    /*
        public int findItemIndex(Book book, List<CartItem> items) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getBook().equals(book)) {
                return i;
            }
        }
        return -1;
    }
     */

}
