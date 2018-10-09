package ru.alex.novotelnov.solution.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alex.novotelnov.data.dao.BookDao;
import ru.alex.novotelnov.data.entity.AuthorEntity;
import ru.alex.novotelnov.data.entity.BookEntity;
import ru.alex.novotelnov.solution.criteria.SearchCriteria;
import ru.alex.novotelnov.solution.search.enums.SearchType;

import java.util.Collection;

@Component
public class LibraryFacade {

    @Autowired
    private BookDao bookDAO;

    @Autowired
    private SearchCriteria searchCriteria;

    private Collection<BookEntity> books;

    public Collection<BookEntity> getBooks() {
        if (books == null){
            updateBookList();
        }
        return books;
    }

    private void updateBookList() {
        books = bookDAO.getBooks();
    }

    public void searchBooksByGenre() {
        books = bookDAO.getBooks(searchCriteria.getGenre());
    }

    public void searchBooksByText(SearchType searchType) {

        switch (searchType){
            case TITLE:
                books = bookDAO.getBooks(searchCriteria.getText());
                break;
            case AUTHOR:
                books = bookDAO.getBooks(new AuthorEntity(searchCriteria.getText()));
                break;
        }
    }

    //todo: m.b. need interceptor
    public void removeBook(BookEntity bookEntity)
    {
        bookDAO.deleteBook(bookEntity.getId());
        updateBookList();
    }

    public void updateBook(BookEntity bookEntity)
    {
        bookDAO.editBook(bookEntity);
        updateBookList();
    }


}
