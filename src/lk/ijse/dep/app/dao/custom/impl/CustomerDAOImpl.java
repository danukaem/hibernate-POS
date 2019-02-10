package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.CrudUtil;
import lk.ijse.dep.app.dao.custom.CustomerDAO;
import lk.ijse.dep.app.db.HibernateUtil;
import lk.ijse.dep.app.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public Optional<Customer> find(String customerId) throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = session.find(Customer.class, customerId);
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(customer);
    }

    public Optional<List<Customer>> findAll() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM Customer";
        List<Customer> customers = session.createNativeQuery(sql, Customer.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(customers);
    }

    public boolean save(Customer customer) throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String saved = (String) session.save(customer);
        session.getTransaction().commit();
        session.close();
        return !saved.equals("");
    }

    public boolean update(Customer customer) throws Exception {
        try (
                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(customer);
            session.getTransaction().commit();
            session.close();
            return true;
        }
    }

    public boolean delete(String customerId) throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Customer customer = new Customer();
        customer.setId(customerId);
        session.delete(customer);
        session.getTransaction().commit();
        session.close();
        return true;


    }

}
