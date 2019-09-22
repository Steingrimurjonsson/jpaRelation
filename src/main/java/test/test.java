/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author stein
 */
public class test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        Customer c1 = new Customer("Bob", "Saget");
        c1.addHobby("Hobby1");
        c1.addHobby("Hobby2");
        c1.addHobby("Hobby3");
        c1.addPhone("463346346", "Phone1");
        c1.addPhone("346377747", "Phone2");

        Customer c2 = new Customer("Stein", "Jonsson");
        c2.addHobby("Hobby4");
        c2.addHobby("Hobby5");
        c2.addPhone("234234234", "Phone1");
        c2.addPhone("653463663", "Phone2");

        try {
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

}
