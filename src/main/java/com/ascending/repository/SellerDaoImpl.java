package com.ascending.repository;
import com.ascending.model.Location;
import com.ascending.model.Seller;
import com.ascending.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class SellerDaoImpl implements SellerDao {

    private SessionFactory sessionFactory;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public List<Seller> getSellers() {
        String hql = "FROM Seller";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Seller> query = session.createQuery(hql);
            return query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        }
    }

    @Override
    public Seller save(Seller seller) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(seller);
            transaction.commit();
            return seller;
        }
        catch(Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean delete(String sellerName) {
        String hql = "DELETE Seller where name = :seller1";
        int deletCount = 0;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Query<Location> query = session.createQuery(hql);
            query.setParameter("seller1", sellerName);
            deletCount = query.executeUpdate();
            return true;
        }
        catch(Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The seller %s was deleted", sellerName));
        return false;
    }
}
