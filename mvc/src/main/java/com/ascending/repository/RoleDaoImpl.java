package com.ascending.repository;

import com.ascending.model.Role;
import com.ascending.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Role getRoleById(Long id) {
        String hql = "FROM Role as p where p.id =:id";
        try (Session session = sessionFactory.openSession()) {

            Query<Role> query = session.createQuery(hql);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    @Override
    public Role save(Role role) {
        Transaction transaction = null;
        try{
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(role);
            transaction.commit();
            return role;
        }
        catch(Exception e){
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        return null;
    }
}
