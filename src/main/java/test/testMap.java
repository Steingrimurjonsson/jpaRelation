package test;

import entities.Address;
import entities.CustomerMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author stein
 */
public class testMap {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Persistence.generateSchema("pu", null);
       
        CustomerMap c3 = new CustomerMap("Someyoungguy", "Rode");
        Address a1 = new Address("SomeRoad99", "SomeCity99");
        CustomerMap c1 = new CustomerMap("Ivan", "Johnson");
        c1.addAddress(new Address("SomeRoad1", "SomeCity1"));
        c1.addAddress(new Address("SomeRoad2", "SomeCity1"));
        
        CustomerMap c2 = new CustomerMap("Somedude", "Blue");
        c2.addAddress(new Address("SomeRoad33", "SomeCity44"));
        c2.addAddress(new Address("SomeRoad44", "SomeCity55"));
        
        a1.addCustomerMap(c2);
        a1.addCustomerMap(c3);
        try {
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.persist(a1);
            em.getTransaction().commit();
            // System.out.println(c1.getId());
            //   System.out.println(c2.getId());

        } finally {
            em.close();
        }

    }

}
