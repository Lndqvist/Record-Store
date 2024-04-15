package se.iths.java23.petterlundqvist.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.iths.java23.petterlundqvist.webshop.service.CartService;
import se.iths.java23.petterlundqvist.webshop.service.RecordService;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/cart")
    public String viewCart(Model m) {
        m.addAttribute("cart", cartService.getCart());
        m.addAttribute("totalPrice", cartService.totalPrice());
        return "cart-page";
    }
    @PostMapping(value="/cart", params= "quantity")
    public String updateQuantity(@RequestParam("amount") int amount,
                                 @RequestParam("id") int id) {
        cartService.updateCart(id, amount);
        return "redirect:/cart";
    }
    @PostMapping(value="/cart", params= "delete")
    public String removeFromCart(@RequestParam("id") int id) {
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }
}
