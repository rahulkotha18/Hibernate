package com.myfirstwebapp.DAO;

import com.myfirstwebapp.Entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    //inject hibernate session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override

    public List<Customer> getCustomers() {
        //get hibernate session
        Session session = sessionFactory.getCurrentSession();

        //create query and getresultlist
        //Query<Customer> theQuery = session.createQuery("from Customer");
        List<Customer> theresult = session.createQuery("from Customer order by firstName").getResultList();

        //return the list
        return theresult;
    }

    @Override
    public void saveCustomer(Customer customer) {
        //get hibernate session
        Session session = sessionFactory.getCurrentSession();

        //save or update the customer
        session.saveOrUpdate(customer);

    }

    @Override
    public Customer getCustomer(int theId) {
        //get hibernate session
        Session session = sessionFactory.getCurrentSession();

        //get the customer and return
        return session.get(Customer.class,theId);

    }

    @Override
    public void deleteCustomer(int theId) {
        //get hibernate session
        Session session = sessionFactory.getCurrentSession();
/*
        //get the customer
        Customer customer = session.get(Customer.class,theId);

        //delete the customer
        session.delete(customer);
 */
        Query query = session.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId",theId);

        query.executeUpdate();
    }
}
