/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.scrap.entity.DAOImpl;

import com.leapfrog.scrap.entity.DAO.LinkDAO;
import com.leapfrog.scrap.entity.Link;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Mr. Shrestha Suman
 */
public class LinkDAOImpl implements LinkDAO {

    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tran;

    public LinkDAOImpl() {
        emf = Persistence.createEntityManagerFactory("JPAU");
        em = emf.createEntityManager();

    }

    @Override
    public int insert(Link l) {
        tran = em.getTransaction();
        tran.begin();
        em.persist(l);

        em.flush();
        tran.commit();

        return l.getId();
    }

}
