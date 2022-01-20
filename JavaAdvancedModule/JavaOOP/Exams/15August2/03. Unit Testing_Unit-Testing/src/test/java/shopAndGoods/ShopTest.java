package shopAndGoods;


import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ShopTest {
    // TODO
    private Map<String, Goods> shelves;
    private Shop shop;


    @Before
    public void init() {
        shelves = getShelvesStub();
        shop = new Shop();
    }

    private Map<String, Goods> getShelvesStub() {

        Map<String, Goods> shelves = new LinkedHashMap<>();

        shelves.put("Shelves1", new Goods("g1","1"));
        shelves.put("Shelves2", new Goods("g2","2"));
        shelves.put("Shelves3", new Goods("g3","3"));
        shelves.put("Shelves4", new Goods("g4","4"));
        shelves.put("Shelves5", new Goods("g5","5"));
        shelves.put("Shelves6", new Goods("g6","6"));
        shelves.put("Shelves7", new Goods("g7","7"));
        shelves.put("Shelves8", new Goods("g8","8"));
        shelves.put("Shelves9", new Goods("g9","9"));
        shelves.put("Shelves10", new Goods("g10","10"));
        shelves.put("Shelves11", new Goods("g11","11"));
        shelves.put("Shelves12", new Goods("g12","12"));

        return shelves;
    }

    @Test
    public void testGetShelves() {
        assertEquals(12, shop.getShelves().size());
        assertEquals(null, shop.getShelves().get("Shelves1"));
    }

    @Test
    public void testAddGoodsSuccess() throws OperationNotSupportedException {
        String result = shop.addGoods("Shelves1", this.shelves.get("Shelves1"));
        String expected = String.format("Goods: %s is placed successfully!",
                this.shelves.get("Shelves1").getGoodsCode());
        assertEquals(expected, result);
        assertEquals(this.shelves.get("Shelves1"), shop.getShelves().get("Shelves1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsShelfDoesNotExist() throws OperationNotSupportedException {
        String result = shop.addGoods("Shelves", this.shelves.get("Shelves1"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsShelfAlreadyTaken() throws OperationNotSupportedException {
        String result = shop.addGoods("Shelves1", this.shelves.get("Shelves1"));
        shop.addGoods("Shelves1", this.shelves.get("Shelves2"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsItemExists() throws OperationNotSupportedException {
        String result = shop.addGoods("Shelves1", this.shelves.get("Shelves1"));
        shop.addGoods("Shelves1", this.shelves.get("Shelves1"));

    }

    @Test
    public void testRemoveGoods() throws OperationNotSupportedException {
        String expected = String.format("Goods: %s is removed successfully!",
                this.shelves.get("Shelves1").getGoodsCode());
        shop.addGoods("Shelves1", this.shelves.get("Shelves1"));
        String result = shop.removeGoods("Shelves1", this.shelves.get("Shelves1"));

        assertEquals(expected, result);
        assertEquals(null, shop.getShelves().get("Shelves1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsShelfDoesNotExist() throws OperationNotSupportedException {
        String result = shop.removeGoods("Shelves1", this.shelves.get("Shelves1"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsItemOnShelvesDoesNotExist() throws OperationNotSupportedException {
        String result = shop.removeGoods("Shelves1", this.shelves.get("Shelves2"));

    }

    @Test
    public void testGoodsNameAndCode() throws OperationNotSupportedException {

        shop.addGoods("Shelves1", new Goods("g1","1"));

        assertEquals(shop.getShelves().get("Shelves1").getName(), "g1");
        assertEquals(shop.getShelves().get("Shelves1").getGoodsCode(), "1");
    }
}