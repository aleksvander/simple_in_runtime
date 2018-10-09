package ru.alex.novotelnov.data.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BOOK", schema = "PUBLIC", catalog = "PUBLIC")
public class BookEntity {
    private long id;
    private String name;
    private String description;
    private Integer page_count;
    private GenreEntity genre;
    private AuthorEntity author;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "PAGE_COUNT")
    public Integer getPage_count() {
        return page_count;
    }

    public void setPage_count(Integer page_count) {
        this.page_count = page_count;
    }

        @Basic
//    @Column(name = "GENRE_ID")
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "GENRE_ID", referencedColumnName = "ID", nullable = false)
    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

        @Basic
//    @Column(name = "AUTHOR_ID")
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID", nullable = false)
    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return id == that.id &&
                genre.getId() == that.genre.getId() &&
                author.getId() == that.author.getId() &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(page_count, that.page_count); //todo: why objects?
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, page_count, genre.getId(), author.getId());
    }
}
