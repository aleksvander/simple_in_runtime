package ru.alex.novotelnov.data.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "AUTHOR", schema = "PUBLIC", catalog = "PUBLIC")
public class AuthorEntity {
    private long id;
    private String name;
    private Date dateBirthday;

    public AuthorEntity() {}
    public AuthorEntity (String name)
    {
        this.name = name;
    }

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
    @Column(name = "DATE_BIRTHDAY")
    public Date getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(Date dateBirthday) {
        this.dateBirthday = dateBirthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(dateBirthday, that.dateBirthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateBirthday);
    }
}
