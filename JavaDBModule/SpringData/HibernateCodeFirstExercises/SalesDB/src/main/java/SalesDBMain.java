import entities.Customer;
import entities.Product;
import entities.Sale;
import entities.StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SalesDBMain {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("sales");

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        Sale saleOne = new Sale();
        saleOne.setDate(new Date());

        Sale saleTwo = new Sale();
        saleTwo.setDate(new Date());

        Customer customer = new Customer();
        customer.setName("Flinny Bone");
        customer.setEmail("bone@mail.com");


        Product product = new Product();
        product.setName("Viper 3");
        product.setPrice(BigDecimal.valueOf(10.5d));
        product.setQuantity(2d);

        StoreLocation storeLocation = new StoreLocation();
        storeLocation.setLocationName("Brown Store");

//        customer.getSales().add(saleOne);
//        customer.getSales().add(saleTwo);
//
//        product.getSales().add(saleOne);
//        product.getSales().add(saleTwo);
//
//        storeLocation.getSales().add(saleOne);
//        storeLocation.getSales().add(saleTwo);


        saleOne.setCustomer(customer);
        saleOne.setProduct(product);
        saleOne.setStoreLocation(storeLocation);

        saleTwo.setCustomer(customer);
        saleTwo.setProduct(product);
        saleTwo.setStoreLocation(storeLocation);


        em.persist(customer);
        em.persist(product);
        em.persist(storeLocation);
        em.persist(saleOne);
        em.persist(saleTwo);

        em.flush();
        em.getTransaction().commit();
        em.close();
    }
}
