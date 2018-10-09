package ru.alex.novotelnov.solution.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alex.novotelnov.data.dao.GenreDao;
import ru.alex.novotelnov.data.entity.GenreEntity;

import java.util.ArrayList;
import java.util.Collection;

//todo: delete it
@Component
public class GenreFacade {

    @Autowired
    private GenreDao genreDao;

    private Collection<GenreEntity> genres;

    public Collection<GenreEntity> getGenres() {
        if (genres == null){
            updateGenreList();
        }
        return genres;
    }

    private void updateGenreList() {
        genres = genreDao.getGenres();
    }

    public Collection<Long> getGenreIds()
    {
        //todo
        getGenres();

        Collection<Long> ids = new ArrayList<>();
        for (GenreEntity genre : genres)
        {
            ((ArrayList<Long>) ids).add(genre.getId());
        }

        return ids;
    }
}
