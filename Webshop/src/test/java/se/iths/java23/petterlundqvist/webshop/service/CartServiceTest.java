package se.iths.java23.petterlundqvist.webshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.iths.java23.petterlundqvist.webshop.model.Category;
import se.iths.java23.petterlundqvist.webshop.model.Record;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceTest {

    @Autowired
    CartService cartService;

    @BeforeEach
    void setUp() {
        cartService.emptyCart();
    }

    @Test
    void cartNotEmpty() {
        Record r1 = new Record("R1", "A1", 0, new Category());
        r1.setIdItem(1);
        cartService.addToCart(r1, 1);
        assertFalse(cartService.getCart().isEmpty());
    }

    @Test
    void addRecordTwice() {
        Record r1 = new Record("R1", "A1", 0, new Category());
        r1.setIdItem(1);
        cartService.addToCart(r1, 1);
        cartService.addToCart(r1, 1);
        assertEquals(1, cartService.getCart().size());
    }

    @Test
    void addRecordDifferentQuantity() {
        Record r1 = new Record("R1", "A1", 0, new Category());
        r1.setIdItem(1);
        cartService.addToCart(r1, 1);
        cartService.addToCart(r1, 2);
        assertEquals(2, cartService.getCart().get(r1));
    }

    @Test
    void testTotalPrice(){
        Record r1 = new Record("R1", "A1", 10, new Category());
        r1.setIdItem(1);
        Record r2 = new Record("R2", "A2", 20, new Category());
        r2.setIdItem(2);
        cartService.addToCart(r1, 3);
        cartService.addToCart(r2, 1);
        System.out.println(cartService.getCart());
        assertEquals(50, cartService.totalPrice());
    }

    @Test
    void removeFromCart(){
        Record r1 = new Record("R1", "A1", 10, new Category());
        r1.setIdItem(1);
        cartService.addToCart(r1, 1);
        cartService.removeFromCart(r1.getIdRecord());
        assertTrue(cartService.getCart().isEmpty());
    }
}