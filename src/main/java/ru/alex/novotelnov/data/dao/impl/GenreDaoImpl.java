package ru.alex.novotelnov.data.dao.impl;

import org.springframework.stereotype.Repository;
import ru.alex.novotelnov.data.dao.GenreDao;
import ru.alex.novotelnov.data.entity.AuthorEntity;
import ru.alex.novotelnov.data.entity.GenreEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

@Repository
public class GenreDaoImpl implements GenreDao {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public Collection<GenreEntity> getGenres() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GenreEntity> cq = cb.createQuery(GenreEntity.class);
        Root<GenreEntity> genreEntity = cq.from(GenreEntity.class);
        cq.select(genreEntity);
        TypedQuery<GenreEntity> q = em.createQuery(cq);
        return q.getResultList();
    }
}
