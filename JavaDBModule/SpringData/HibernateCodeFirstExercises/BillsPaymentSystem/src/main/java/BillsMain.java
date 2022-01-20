

import entities.BankAccount;
import entities.BillingDetails;
import entities.CreditCard;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BillsMain {

    public static void main(String[] args) {

        User user = new User();
        user.setFirstName("Don");
        user.setLastName("Wilson");


        BillingDetails cc = new CreditCard(124324, user);

        BillingDetails ba = new BankAccount(2345, user);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bills");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(cc);
        em.persist(ba);

        em.getTransaction().commit();;
        em.close();
    }
}
