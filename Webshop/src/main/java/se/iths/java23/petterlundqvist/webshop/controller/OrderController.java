package se.iths.java23.petterlundqvist.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.iths.java23.petterlundqvist.webshop.model.Order;
import se.iths.java23.petterlundqvist.webshop.service.OrderService;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/checkout")
    public String checkout(Model m) {
        Order order = orderService.createOrder();
        m.addAttribute("order", order.getOrderLines());
        m.addAttribute("totalPrice", orderService.totalPrice(order));

        return "checkout-page";
    }

    @GetMapping("/admin-menu")
    public String adminMenu(Model m) {
        m.addAttribute("orders", orderService.getAllOrders());
        return "admin-menu-page";
    }
    @GetMapping("/order/{id}")
    public String viewOrder(Model m, @PathVariable("id") int id) {
        m.addAttribute("order", orderService.getOrderById(id));
        return "order-page";
    }
    @PostMapping("/order/{id}")
    public String processOrder(@PathVariable("id") int id) {
        orderService.processOrder(id);
        return "redirect:/order/{id}";
    }

}
