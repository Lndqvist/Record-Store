package se.iths.java23.petterlundqvist.webshop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_line", schema = "webshop")
public class OrderLine {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_order_line")
    private int idOrderLine;

    private int quantity;
    @ManyToOne
    @JoinColumn(name = "record", referencedColumnName = "id_record")
    private Record record;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id_customer_order")
    private Order order;

    public OrderLine(int quantity, Record record, Order order) {
        this.quantity = quantity;
        this.record = record;
        this.order = order;
    }

    public OrderLine() {
    }

    public int getIdOrderLine() {
        return idOrderLine;
    }

    public void setIdOrderLine(int idOrderLine) {
        this.idOrderLine = idOrderLine;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrderId() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}