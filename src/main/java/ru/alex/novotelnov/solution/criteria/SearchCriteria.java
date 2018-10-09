package ru.alex.novotelnov.solution.criteria;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.alex.novotelnov.data.entity.GenreEntity;
import ru.alex.novotelnov.solution.search.enums.SearchType;

import java.io.Serializable;

@Component
@Scope("singleton")
public class SearchCriteria implements Serializable {

    private String text;
    private SearchType searchType = SearchType.TITLE;
    private GenreEntity genreEntity;
    private GenreEntity genre;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

    public GenreEntity getGenreEntity() {
        return genreEntity;
    }

    public void setGenreEntity(GenreEntity genreEntity) {
        this.genreEntity = genreEntity;
    }

    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }
}
