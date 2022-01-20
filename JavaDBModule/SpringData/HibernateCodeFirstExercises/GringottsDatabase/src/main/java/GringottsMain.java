import entities.WizardDeposit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GringottsMain {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("gringotts");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        WizardDeposit wd = new WizardDeposit();

        wd.setFirstName("Barny");
        wd.setLastName("Rubble");
        wd.setAge(34);
        wd.setDepositAmount(2000d);

        em.persist(wd);

        em.getTransaction().commit();
        em.close();
    }
}
