package se.iths.java23.petterlundqvist.webshop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.iths.java23.petterlundqvist.webshop.model.Record;

import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class CartService {
    private HashMap<Record, Integer> cart = new HashMap<>();

    public void addToCart(Record r, int amount) {
        boolean existed = false;
        for(Map.Entry<Record, Integer> set : cart.entrySet()) {
            if(set.getKey().getIdRecord() == r.getIdRecord()) {
                cart.put(set.getKey(), amount);
                existed = true;
                break;
            }
        }
        if(!existed) {
            cart.put(r, amount);
        }
    }

    public void updateCart(int id, int amount) {
        Record r;
        for (Map.Entry<Record, Integer> set : cart.entrySet()) {
            if (set.getKey().getIdRecord() == id) {
                r = set.getKey();
                cart.put(r, amount);
                break;
            }
        }
    }

    public void removeFromCart(int id) {
        cart.entrySet().removeIf(r -> r.getKey().getIdRecord() == id);
    }

    public HashMap<Record, Integer> getCart(){
        return cart;
    }

    public int totalPrice() {
        int sum = 0;
        for (Map.Entry<Record, Integer> set : cart.entrySet()) {
            sum += set.getKey().getPrice() * set.getValue();
        }
        return sum;
    }

    public void emptyCart() {
        cart.clear();
    }

}
