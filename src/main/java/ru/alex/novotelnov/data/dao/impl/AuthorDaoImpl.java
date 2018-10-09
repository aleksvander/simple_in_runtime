package ru.alex.novotelnov.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.novotelnov.data.dao.AuthorDao;
import ru.alex.novotelnov.data.entity.AuthorEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Autowired
    private LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean;

    @Transactional(readOnly = true)
    public Collection<AuthorEntity> getAuthors() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AuthorEntity> cq = cb.createQuery(AuthorEntity.class);
        Root<AuthorEntity> authorEntity = cq.from(AuthorEntity.class);
        cq.select(authorEntity);
        TypedQuery<AuthorEntity> q = em.createQuery(cq);
        return q.getResultList();
    }


}