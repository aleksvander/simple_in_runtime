package ru.alex.novotelnov.data.dao;

import ru.alex.novotelnov.data.entity.AuthorEntity;
import ru.alex.novotelnov.data.entity.BookEntity;
import ru.alex.novotelnov.data.entity.GenreEntity;

import java.util.Collection;

public interface BookDao {
    Collection<BookEntity> getBooks();
    Collection<BookEntity> getBooks(AuthorEntity authorEntity);
    Collection<BookEntity> getBooks(String bookName);
    Collection<BookEntity> getBooks(GenreEntity genreEntity);


    void editBook(BookEntity bookEntity);
    void deleteBook(Long id);
}
