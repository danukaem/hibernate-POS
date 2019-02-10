package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.CrudUtil;
import lk.ijse.dep.app.dao.custom.ItemDAO;
import lk.ijse.dep.app.db.HibernateUtil;
import lk.ijse.dep.app.entity.Customer;
import lk.ijse.dep.app.entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDAOImpl implements ItemDAO {
    public Optional<List<Item>> findAll() throws Exception {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM Item";
        List<Item> items = session.createNativeQuery(sql, Item.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(items);
    }

    public boolean save(Item item) throws Exception {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String saved = (String) session.save(item);
        session.getTransaction().commit();
        session.close();
        return !saved.equals("");
    }

    public boolean update(Item item) throws Exception {

        try (
                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            session.close();
            return true;
        }
    }

    public boolean delete(String code) throws Exception {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Item item = new Item();
        item.setCode(code);
        session.delete(item);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Optional<Item> find(String itemCode) throws Exception {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Item item = session.find(Item.class, itemCode);
        session.getTransaction().commit();
        session.close();
        return Optional.ofNullable(item);

    }
}
