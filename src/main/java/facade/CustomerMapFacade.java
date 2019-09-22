/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.Address;
import entities.CustomerMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author stein
 */
public class CustomerMapFacade {

    private static EntityManagerFactory emf;
    private static CustomerMapFacade instance;

    public CustomerMapFacade() {
    }

    public static CustomerMapFacade getCustomerMapFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerMapFacade();
        }
        return instance;
    }

    public CustomerMap getCustomerMap(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            CustomerMap c = em.find(CustomerMap.class, id);
            return c;
        } finally {
            em.close();
        }
    }

    public List<CustomerMap> getCustomersMap() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery q1 = em.createQuery("Select c from CustomerMap c", CustomerMap.class);
            return q1.getResultList();
        } finally {
            em.close();
        }
    }

    public CustomerMap addCustomerMap(CustomerMap cust) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }

    public CustomerMap deleteCustomerMap(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            CustomerMap c = getCustomerMap(id);
            Query q1 = em.createQuery("delete from CustomerMap c where c.id = :id").setParameter("id", id);
            em.getTransaction().begin();
            q1.executeUpdate();
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }
    }

    public CustomerMap editCustomerMap(CustomerMap cust) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q1 = em.createQuery("UPDATE CustomerMap c SET c.firstName = :firstName, c.lastName = :lastName, c.addresses = :addresses WHERE c.id = :id")
                    .setParameter("firstName", cust.getFirstName())
                    .setParameter("lastName", cust.getLastName())
                    .setParameter("addresses", cust.getAddresses())
                    .setParameter("id", cust.getId());
            em.getTransaction().begin();
            q1.executeUpdate();
            em.getTransaction().commit();
            return getCustomerMap(cust.getId());
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerMapFacade cf = CustomerMapFacade.getCustomerMapFacade(emf);
        CustomerMap c1 = new CustomerMap("Ivan", "Johnson");
        c1.addAddress(new Address("SomeRoad1", "SomeCity1"));
        c1.addAddress(new Address("SomeRoad1", "SomeCity2"));
     
    }
}
