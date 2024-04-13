package se.iths.java23.petterlundqvist.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import se.iths.java23.petterlundqvist.webshop.model.Order;
import se.iths.java23.petterlundqvist.webshop.model.OrderLine;
import se.iths.java23.petterlundqvist.webshop.model.Record;
import se.iths.java23.petterlundqvist.webshop.model.User;
import se.iths.java23.petterlundqvist.webshop.repository.OrderLineRepository;
import se.iths.java23.petterlundqvist.webshop.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderLineRepository orderLineRepository;

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    MailService mailService;

    public Order createOrder() {
        HashMap<Record, Integer> cart = cartService.getCart();
        User user = userService.getSignedInUser();
        Order order = null;
        ArrayList<OrderLine> orderLines = new ArrayList<>();
        if(!cart.isEmpty()) {
            order = new Order(LocalDateTime.now(), false, user);
            orderRepository.save(order);
            for (Map.Entry<Record, Integer> set : cart.entrySet()) {
                OrderLine orderLine = new OrderLine(set.getValue(), set.getKey(), order);
                orderLines.add(orderLine);
                orderLineRepository.save(orderLine);
            }
            order.setOrderLines(orderLines);
            mailService.formatMail(order);
        }
        cartService.emptyCart();
        return order;
    }

    public int totalPrice(Order order) {
        int sum = 0;
        for(OrderLine o : order.getOrderLines()) {
            sum += o.getRecord().getPrice() * o.getQuantity();
        }
        return sum;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int id) {
        return orderRepository.getReferenceById(id);
    }

    public void processOrder(int id) {
        Order order = getOrderById(id);
        order.setStatus(true);
        orderRepository.save(order);
    }
}
