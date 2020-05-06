package com.ascending.repository;

import com.ascending.model.Image;
import com.ascending.model.Location;
import com.ascending.service.ImageService;
import com.ascending.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDaoImpl implements ImageDao{

    private SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Image save(Image image) {
        Transaction transaction = null;
        try{
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(image);
            transaction.commit();
            return image;
        }
        catch(Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean deleteByFileName(String fileName) {
        String hql = "DELETE Image where fileName = :fileName";
        int deleteCount = 0;
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Query<Location> query = session.createQuery(hql);
            query.setParameter("fileName", fileName);
            deleteCount = query.executeUpdate();
            return true;
        }
        catch(Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The location %s was deleted", fileName));
        return false;
    }
}
