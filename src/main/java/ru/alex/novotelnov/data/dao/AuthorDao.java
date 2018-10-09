package ru.alex.novotelnov.data.dao;

import ru.alex.novotelnov.data.entity.AuthorEntity;

import java.util.Collection;

public interface AuthorDao {
    Collection<AuthorEntity> getAuthors();
}
