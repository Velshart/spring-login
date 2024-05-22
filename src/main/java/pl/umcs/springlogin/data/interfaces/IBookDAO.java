package pl.umcs.springlogin.data.interfaces;

import pl.umcs.springlogin.data.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    Optional<Book> getById(int id);
    List<Book> getAll();
    void saveOrUpdate(Book book);
    void delete(int id);
}
