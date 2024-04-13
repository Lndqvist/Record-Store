package se.iths.java23.petterlundqvist.webshop.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "customer_order")
@Entity
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_customer_order")
    private int idOrder;

    private LocalDateTime date;

    private boolean status;
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id_user")
    private User user;

    public Order(LocalDateTime date, boolean status, User user) {
        this.date = date;
        this.status = status;
        this.user = user;
    }

    public Order() {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}