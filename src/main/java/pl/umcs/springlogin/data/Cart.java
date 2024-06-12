package pl.umcs.springlogin.data;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @OneToOne(mappedBy = "cart")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*
        public void addItem(Book book, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setQuantity(quantity);

        items.add(cartItem);
    }
    */


    public void addItem(Book book, int quantity) {
        for (CartItem item : items) {
            if (item.getBook().equals(book)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        CartItem newItem = new CartItem();
        newItem.setCart(this);
        newItem.setBook(book);
        newItem.setQuantity(quantity);
        items.add(newItem);
    }

    /*
        public void removeItem(Book book) {
        items.removeIf(cartItem -> cartItem.getBook().equals(book));
    }
     */

    public void removeItem(Book book, int quantity) {
        for (CartItem item : items) {
            if (item.getBook().equals(book)) {
                item.setQuantity(item.getQuantity() - 1);
                if (item.getQuantity() <= 0) {
                    items.remove(item);
                }
                return;
            }
        }
        throw new RuntimeException("Book not found in cart");
    }

}
