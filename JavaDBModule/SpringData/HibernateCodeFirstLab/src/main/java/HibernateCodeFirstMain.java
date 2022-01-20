import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateCodeFirstMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("shampoo_company");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        BasicIngredient am = new AmoniumChloride();
        BasicIngredient mint = new Mint();
        BasicIngredient nettle = new Nettle();

        BasicLabel label =
                new BasicLabel("Fresh Nuke Shampoo","contains mint and nettle");

        BasicShampoo freshNuke = new FreshNuke(label);

        freshNuke.getIngredients().add(mint);
        freshNuke.getIngredients().add(nettle);
        freshNuke.getIngredients().add(am);

        em.persist(freshNuke);
        em.getTransaction().commit();
        em.close();
    }
}
