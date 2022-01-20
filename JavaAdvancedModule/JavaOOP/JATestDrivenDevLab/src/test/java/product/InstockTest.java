package product;

import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InstockTest {

    private ProductStock productStock;
    private Product defaultProduct;

    private static final String LABEL = "test_label";
    private static final Double PRICE = 9.99d;
    private static final int QUANTITY = 1;

    @Before
    public void init() {

        productStock = new Instock();
        defaultProduct = new Product(LABEL, PRICE, QUANTITY);
    }

    // add
    // adds product not with the same label
    @Test
    public void testAddingProductInStock() {

        productStock.add(defaultProduct);

        assertTrue(productStock.contains(defaultProduct));
    }


    // contains
    // returns true or false
    @Test
    public void testContainsShouldReturnFalseWhenProductNotPresentAndProductAdded() {

        assertFalse(productStock.contains(defaultProduct));
        productStock.add(defaultProduct);
        assertTrue(productStock.contains(defaultProduct));

    }


    @Test
    public void testAddingNotTheSameProductInStock() {

        productStock.add(defaultProduct);
        productStock.add(defaultProduct);

        assertEquals(1, productStock.getCount());
    }

    @Test
    public void testShouldReturnTheCorrectNumberOfProducts() {
        // assert 0 when empty
        assertEquals(0, productStock.getCount());

        productStock.add(defaultProduct);
        assertEquals(1, productStock.getCount());

    }

    @Test
    public void testFindByIndexShouldReturnTheCorrectProductWhenOnlyOnePresent() {

        productStock.add(defaultProduct);

        Product product = productStock.find(0);

        assertNotNull(product);
        assertEquals(defaultProduct.getLabel(), product.getLabel());
    }

    @Test
    public void testFindByIndexShouldReturnTheCorrectProductWhenProductIsBetweenOtherProducts() {

        stubProducts();

        Product product = productStock.find(4);

        assertNotNull(product);
        assertEquals("test_label_5", product.getLabel());
    }

    @Test
    public void testFindByIndexShouldReturnTheCorrectProductWhenProductIsTheLastOne() {

        stubProducts();

        Product product = productStock.find(9);

        assertNotNull(product);
        assertEquals("test_label_10", product.getLabel());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindByIndexShouldFailWhenIndexGreaterThanNine() {

        stubProducts();
        productStock.find(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindByIndexShouldFailWhenStockIsEmpty() {

        productStock.find(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindByIndexShouldFailWhenIndexIsNegative() {

        stubProducts();
        productStock.find(-1);
    }

    @Test
    public void testChangeQuantityShouldUpdateTheCorrectProductWithCorrectAmount() {

        stubProducts();

        productStock.add(defaultProduct);

        assertEquals(defaultProduct.getQuantity(), productStock.find(10).getQuantity());

        productStock.changeQuantity(defaultProduct.getLabel(), 13);
        assertEquals(13, productStock.find(10).getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityShouldFailIfProductIsAbsent() {

        stubProducts();
        productStock.changeQuantity(defaultProduct.getLabel(), 13);
    }

    @Test
    public void testFindProductByLabelShouldReturnTheCorrectProduct() {

        stubProducts();

        productStock.add(defaultProduct);
        Product product = productStock.findByLabel(defaultProduct.getLabel());

        assertNotNull(product);
        assertEquals(defaultProduct.getLabel(), product.getLabel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindProductByLabelShouldFailWhenProductWithLabelIsAbsent() {

        stubProducts();
        Product product = productStock.findByLabel(defaultProduct.getLabel());
    }

    @Test
    public void testFindFirstNProducts() {

        stubProducts();

        Iterable<Product> productIterable = productStock.findFirstByAlphabeticalOrder(8);

        List<Product> productList = createListFromIterable(productIterable);

        assertEquals(8, productList.size());

    }

    @Test
    public void testFindFirstNProductsOrderedAlphabetically() {

        stubProducts();

        Iterable<Product> productIterable = productStock.findFirstByAlphabeticalOrder(8);

        List<Product> productList = createListFromIterable(productIterable);

        Set<String> expectedLabels = new TreeSet<>(productList.stream().
                map(p -> p.getLabel()).collect(Collectors.toSet()));

        int i = 0;
        for (String expectedLabel : expectedLabels) {
            assertEquals(expectedLabel, productList.get(i++).getLabel());
        }
    }

    @Test
    public void testFindFirstNProductsOrderAlphabeticallyShouldReturnEmptyCollectionWhenStockIsZero() {

        Iterable<Product> productIterable = productStock.findFirstByAlphabeticalOrder(0);

        List<Product> productList = createListFromIterable(productIterable);
        assertTrue(productList.isEmpty());

    }

    @Test
    public void testFindFirstNProductsOrderAlphabeticallyShouldReturnEmptyCollectionWhenStockIsTooLarge() {

        stubProducts();
        Iterable<Product> productIterable = productStock.findFirstByAlphabeticalOrder(11);

        List<Product> productList = createListFromIterable(productIterable);
        assertTrue(productList.isEmpty());

    }

    @Test
    public void testFindAllInRangeShouldReturnEmptyCollectionIfNoSuchRangePresent() {

        stubProducts();
        Iterable<Product> productIterable = productStock.findAllInRange(999.99, 99999.99);

        List<Product> productList = createListFromIterable(productIterable);
        assertTrue(productList.isEmpty());


    }

    @Test
    public void testFindAllInRangeShouldReturnTheCorrectRange() {

        stubProducts();
        Iterable<Product> productIterable = productStock.findAllInRange(20.0, 30.0);

        List<Product> productList = createListFromIterable(productIterable);
        assertEquals(4, productList.size());

        for (Product product : productList) {

            assertTrue(product.getPrice() > 20.0 && product.getPrice() <= 30.0);
        }

    }

    @Test
    public void testFindAllInRangeShouldReturnTheCorrectRangeOrderedDesc() {

        stubProducts();
        Iterable<Product> productIterable = productStock.findAllInRange(20.0, 30.0);

        List<Product> productList = createListFromIterable(productIterable);

        List<Product> expectedProductsOrdered  = productList.stream()
        .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
        .collect(Collectors.toList());

        int i = 0;
        for (Product product : productList) {

            assertEquals(expectedProductsOrdered.get(i++).getLabel(), product.getLabel());
        }
    }

    @Test
    public void testFindAllByPriceShouldReturnTheCorrectProducts() {

        stubProducts();
        Iterable<Product> productIterable = productStock.findAllByPrice(10.0);

        List<Product> productList = createListFromIterable(productIterable);
        assertEquals(1, productList.size());

        for (Product product : productList) {

            assertTrue(product.getPrice() == 10.0);
            assertEquals(10.0, product.getPrice(), 0.00);
        }

    }

    @Test
    public void testFindAllByPriceShouldReturnEmptyCollectionIfPriceIsAbsent() {

        Iterable<Product> productIterable = productStock.findAllByPrice(20.0);

        List<Product> productList = createListFromIterable(productIterable);
        assertTrue(productList.isEmpty());

    }

    @Test
    public void testFindFirstMostExpensiveProductsShouldReturnTheCorrectProducts() {


        List<Product> expectedProductListOrderedDesc =
                stubProductsIntoList().stream()
                        .sorted(Comparator.comparing(Product::getPrice).reversed())
                        .limit(5)
                        .collect(Collectors.toList());

        Iterable<Product> productIterable = productStock.findFirstMostExpensiveProducts(5);

        List<Product> productList = createListFromIterable(productIterable);

        int i = 0;
        for (Product expectedProduct: expectedProductListOrderedDesc) {

            assertEquals(expectedProduct.getLabel(), productList.get(i++).getLabel());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindFirstMostExpensiveProductsShouldFailIfCountIsGreaterThanProductsSize() {

        stubProducts();

        productStock.findFirstMostExpensiveProducts(productStock.getCount()+1);
    }

    @Test
    public void testFindAllByQuantityShouldReturnTheCorrectProducts() {

        List<Product> expectedProducts = stubProductsIntoList().stream().limit(2).
                collect(Collectors.toList());

        Iterable<Product> productIterable = productStock.findAllByQuantity(1);

        List<Product> productList = createListFromIterable(productIterable);

        assertTrue(productList.size() == 2);

        int i = 0;
        for (Product expectedProduct: expectedProducts) {
            assertEquals(expectedProduct.getLabel(), productList.get(i++).getLabel());
        }

    }

    @Test
    public void testFindAllByQuantityShouldReturnEmptyCollectionIfQuantityIsAbsent() {

        stubProducts();

        Iterable<Product> productIterable = productStock.findAllByQuantity(5);

        List<Product> productList = createListFromIterable(productIterable);

        assertTrue(productList.isEmpty());

    }

    @Test
    public void testFindAllProductsInStockShouldReturnAllProducts() {
        List<Product> expectedProducts = stubProductsIntoList();

        Iterator<Product> iterator = productStock.iterator();

        assertNotNull(iterator);
        int i = 0;
        while(iterator.hasNext()) {

            Product product = iterator.next();

            assertEquals(expectedProducts.get(i++).getLabel(), product.getLabel());
        }

    }

    private List<Product> createListFromIterable(Iterable<Product> productIterable) {

        // is valid way
        assertNotNull(productIterable);

        List<Product> productList = new ArrayList<>();

        productIterable.forEach(productList::add);

        return productList;
    }

    private List<Product> stubProductsIntoList() {

        List<Product> productList = new ArrayList<>();

        productList.add(new Product("test_label_1",10.0d, 1));
        productList.add(new Product("test_label_2",20.0d, 1));
        productList.add(new Product("test_label_3",14.0d, 2));
        productList.add(new Product("test_label_4",15.0d, 2));
        productList.add(new Product("test_label_5",16.0d, 2));
        productList.add(new Product("test_label_6",17.0d, 2));
        productList.add(new Product("test_label_7",21.0d, 2));
        productList.add(new Product("test_label_8",22.0d, 2));
        productList.add(new Product("test_label_9",23.0d, 2));
        productList.add(new Product("test_label_10",30.0d, 2));

        stubProducts();

        return productList;
    }


    private void stubProducts() {

        productStock.add(new Product("test_label_1",10.0d, 1));
        productStock.add(new Product("test_label_2",20.0d, 1));
        productStock.add(new Product("test_label_3",14.0d, 2));
        productStock.add(new Product("test_label_4",15.0d, 2));
        productStock.add(new Product("test_label_5",16.0d, 2));
        productStock.add(new Product("test_label_6",17.0d, 2));
        productStock.add(new Product("test_label_7",21.0d, 2));
        productStock.add(new Product("test_label_8",22.0d, 2));
        productStock.add(new Product("test_label_9",23.0d, 2));
        productStock.add(new Product("test_label_10",30.0d, 2));

    }



}
