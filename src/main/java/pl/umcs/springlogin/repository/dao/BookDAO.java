package pl.umcs.springlogin.repository.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.stereotype.Repository;
import pl.umcs.springlogin.data.Book;
import pl.umcs.springlogin.data.interfaces.IBookDAO;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDAO implements IBookDAO {

    @PersistenceContext
    private EntityManager entityManager;
    private final String GET_ALL_JPQL = "FROM pl.umcs.springlogin.data.Book";
    private final String GET_BY_ID_JPQL = "SELECT b FROM pl.umcs.springlogin.data.Book b WHERE b.id = :id";

    public BookDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Book> getById(int id) {
        TypedQuery<Book> query = entityManager.createQuery(GET_BY_ID_JPQL, Book.class);
        query.setParameter("id", id);

        try {
            return Optional.of(query.getSingleResult());

        }catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = entityManager.createQuery(GET_ALL_JPQL, Book.class);
        List<Book> result = query.getResultList();
        return result;
    }

    @Override
    public void saveOrUpdate(Book book) {
        System.out.println("BOOK " + book);
        if(getById(book.getId()).isEmpty()) {
            entityManager.persist(book);
        }else {
            entityManager.merge(book);
        }
    }

    @Override
    public void delete(int id) {
        Book book = getById(id).orElse(null);
        if(book != null) {
            entityManager.remove(book);
        }
    }
}
