package shopAndGoods;


import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShopTest {

    private Shop shop;
    private List<Goods> goods;

    @Before
    public void init() {
        this.shop = new Shop();
        this.goods = stubGoods();
    }

    private List<Goods> stubGoods() {

        List<Goods> goods = new ArrayList<>();

        goods.add(new Goods("Rabbit1","1"));
        goods.add(new Goods("Rabbit2","2"));
        goods.add(new Goods("Rabbit3","3"));
        goods.add(new Goods("Rabbit4","4"));
        goods.add(new Goods("Rabbit5","5"));

        return goods;

    }

    @Test
    public void testGetShelves() {

        assertNotNull(shop.getShelves());
        assertEquals(12,shop.getShelves().size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetShelvesShouldFailWhenAddingShelves() {
        shop.getShelves().put("Shelves1", goods.get(0));
    }

    @Test
    public void testAddGoodsShouldReturnSuccess() throws OperationNotSupportedException {

        String result = shop.addGoods("Shelves1", goods.get(0));

        String expectedResult = "Goods: 1 is placed successfully!";

        assertEquals(expectedResult, result);
        assertEquals(goods.get(0).getName(), shop.getShelves().get("Shelves1").getName());
        assertEquals(goods.get(0).getGoodsCode(), shop.getShelves().get("Shelves1").getGoodsCode());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsShouldFailShelfDoesNotExist() throws OperationNotSupportedException {

        shop.addGoods("Shelves13", goods.get(0));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsShouldFailShelfIsAlreadyTaken() throws OperationNotSupportedException {

        shop.addGoods("Shelves1", goods.get(0));
        shop.addGoods("Shelves1", goods.get(1));

    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddGoodsShouldFailGoodAlreadyOnShelf() throws OperationNotSupportedException {

        String result = shop.addGoods("Shelves1", goods.get(0));
        String expectedResult = "Goods: 1 is placed successfully!";
        assertEquals(expectedResult, result);
        shop.addGoods("Shelves2", goods.get(0));

    }

    @Test
    public void testRemoveGoodsShouldReturnSuccess() throws OperationNotSupportedException {

        assertNull(shop.getShelves().get("Shelves1"));
        shop.addGoods("Shelves1", goods.get(0));
        String result = shop.removeGoods("Shelves1", goods.get(0));
        String expectedResult = "Goods: 1 is removed successfully!";

        assertEquals(expectedResult, result);

        assertNull(shop.getShelves().get("Shelves1"));
    }

    @Test
    public void testRemoveGoodsShouldReturnSuccessV2() throws OperationNotSupportedException {

        shop.addGoods("Shelves1", goods.get(0));
        shop.removeGoods("Shelves1", goods.get(0));

        assertNull(shop.getShelves().get("Shelves1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsShouldFailShelfDoesNotExist() {

        shop.removeGoods("Shelves13", goods.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsShouldFailGoodDoesNotExist() {

        shop.removeGoods("Shelves1", goods.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsShouldFailGoodDoesNotExistV2() throws OperationNotSupportedException {
        shop.addGoods("Shelves1",goods.get(1));
        shop.removeGoods("Shelves1", goods.get(0));
    }
}