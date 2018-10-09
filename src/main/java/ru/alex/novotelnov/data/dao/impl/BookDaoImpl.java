package ru.alex.novotelnov.data.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.novotelnov.data.dao.BookDao;
import ru.alex.novotelnov.data.entity.AuthorEntity;
import ru.alex.novotelnov.data.entity.BookEntity;
import ru.alex.novotelnov.data.entity.GenreEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.print.Book;
import java.util.Collection;

@Repository
public class BookDaoImpl implements BookDao {
    private EntityManager em;
    private ProjectionList bookProjection;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public BookDaoImpl() {
        bookProjection = Projections.projectionList();
        bookProjection.add(Projections.property("id"), "id");
        bookProjection.add(Projections.property("name"), "name");
        bookProjection.add(Projections.property("description"), "description");
        bookProjection.add(Projections.property("page_count"), "page_count");
        bookProjection.add(Projections.property("genre"), "genre");
        bookProjection.add(Projections.property("author"), "author");
    }

    /**
     * Get all books
     *
     * @return collection books entity
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<BookEntity> getBooks() {
        Collection<BookEntity> books = createBookList(createBookCriteria());
        for(BookEntity bookEntity : books)
        {
            bookEntity.getAuthor();
        }
        return books;
    }

    /**
     * Find by author name
     *
     * @param authorEntity by name
     * @return collection books entity
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<BookEntity> getBooks(AuthorEntity authorEntity) {
        Collection<BookEntity> books = createBookList(createBookCriteria().add(Restrictions.ilike("author_id.name", authorEntity.getName(), MatchMode.ANYWHERE)));
        return books;
    }

    /**
     * Find by book name
     *
     * @param bookName by name
     * @return collection books entity
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<BookEntity> getBooks(String bookName) {
        Collection<BookEntity> books = createBookList(createBookCriteria().add(Restrictions.ilike("b.name", bookName, MatchMode.ANYWHERE)));
        return books;
    }

    /**
     * Find by Genre id
     *
     * @param genreEntity by id
     * @return collection books entity
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<BookEntity> getBooks(GenreEntity genreEntity) {
        long id = genreEntity.getId();
        System.out.println(id);
        Collection<BookEntity> books = createBookList(createBookCriteria().add(Restrictions.eq("genre_id.id", genreEntity.getId())));
        return books;
    }

    @Transactional
    @Override
    public void editBook(BookEntity bookEntity) {
        if (bookEntity != null)
        {
            em.merge(bookEntity);
            em.flush();
            em.clear();
        }
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {

        BookEntity bookEntity = em.find(BookEntity.class, id);
        if (bookEntity != null) {
            em.remove(bookEntity);
            em.flush();
            em.detach(bookEntity);
        }
    }

    private DetachedCriteria createBookCriteria() {
        DetachedCriteria bookListCriteria = DetachedCriteria.forClass(BookEntity.class, "b");
        createAlias(bookListCriteria);
        return bookListCriteria;
    }

    private void createAlias(DetachedCriteria criteria) {
        criteria.createAlias("b.author", "author_id");
        criteria.createAlias("b.genre", "genre_id");
    }

    private Collection<BookEntity> createBookList(DetachedCriteria bookListCriteria) {
        Session session = em.unwrap(Session.class);
        Criteria criteria = bookListCriteria.getExecutableCriteria(session);
        criteria.addOrder(Order.asc("b.name")).setProjection(bookProjection);
        criteria.setResultTransformer(Transformers.aliasToBean(BookEntity.class));
        return criteria.list();
    }

    private void setFeatchMode(Criteria criteria) {
        criteria.setFetchMode("genre_id", FetchMode.JOIN);
        criteria.setFetchMode("genre_id", FetchMode.JOIN);
    }
}
