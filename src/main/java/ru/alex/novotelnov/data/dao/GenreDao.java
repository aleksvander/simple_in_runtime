package ru.alex.novotelnov.data.dao;

import ru.alex.novotelnov.data.entity.AuthorEntity;
import ru.alex.novotelnov.data.entity.GenreEntity;

import java.util.Collection;

public interface GenreDao {
    Collection<GenreEntity> getGenres();
}
