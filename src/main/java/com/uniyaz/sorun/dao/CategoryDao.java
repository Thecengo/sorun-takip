package com.uniyaz.sorun.dao;

import com.uniyaz.sorun.domain.Category;
import com.uniyaz.sorun.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDao {

    public Category saveCategory(Category category){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();

            category = (Category) session.merge(category);

            session.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return category;
    }

    public List<Category> findAllCategory() {

        List<Category> categoryList = null;

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try (Session session = sessionFactory.openSession();) {

            Query query = session.createQuery("Select category From Category category");
            categoryList = query.list();

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

        return categoryList;
    }

}
