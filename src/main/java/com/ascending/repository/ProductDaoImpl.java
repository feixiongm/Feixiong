package com.ascending.repository;

import com.ascending.model.Product;
import com.ascending.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<Product> getProduct() {
        String hql = "FROM Product";
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery(hql);
            return query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        }
    }

    @Override
    public Product save(Product product) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(product);
            transaction.commit();
            return product;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean deleteByname(String proName) {
        String hql = "DELETE Product where name = :product1";
        int deletCount = 0;
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<Product> query = session.createQuery(hql);
            query.setParameter("product1", proName);
            deletCount = query.executeUpdate();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The product %s was deleted", proName));
        return false;
    }

    @Override
    public Product getProductById(Long productId) {

        String hql = "FROM Product as p where p.id =:id";
        try (Session session = sessionFactory.openSession()) {

            Query<Product> query = session.createQuery(hql);
            query.setParameter("id", productId);
            return query.uniqueResult();
        }
    }
}
