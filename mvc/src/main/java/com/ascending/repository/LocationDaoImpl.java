package com.ascending.repository;

import com.ascending.model.Location;
import com.ascending.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class LocationDaoImpl implements LocationDao{

    private SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Set<Location> getLocations() {
        String hql = "FROM Location  as loca left join fetch loca.products";
        try(Session session = sessionFactory.openSession()){
            Query<Location> query = session.createQuery(hql);
            List<Location> result = query.getResultList();
            Set<Location> setResult = result.stream().collect(Collectors.toSet());
            return setResult;
        }
    }

    @Override
//    @Transactional
    public Location save(Location location) {
        Transaction transaction = null;
        try{
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(location);
            transaction.commit();
            return location;
        }
        catch(Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean deleteByName(String locaName) {
        String hql = "DELETE Location where name = :location1";
        int deleteCount = 0;
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Query<Location> query = session.createQuery(hql);
            query.setParameter("location1", locaName);
            deleteCount = query.executeUpdate();
            return true;
        }
        catch(Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The location %s was deleted", locaName));
        return false;
    }
    @Override
    public List<Location> getLocationAndProducts(String name) {
        if (name == null) return null;
        String hql = "FROM Location as loca left join fetch loca.products where lower(loca.name) = :name";
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(hql);
            query.setParameter("name", name.toLowerCase());

            List<Location> resultList = query.list();
            return resultList;
        }

            }
    @Override
    public Location getLocationById (Long locationId){

        if(locationId <= 0) return null;

        String hql = "FROM Location as b left join fetch b.products where b.id=:id";
        try (Session session = sessionFactory.openSession()) {

            Query<Location> query = session.createQuery(hql);
            query.setParameter("id", locationId);
            return query.uniqueResult();
        }
    }
}
