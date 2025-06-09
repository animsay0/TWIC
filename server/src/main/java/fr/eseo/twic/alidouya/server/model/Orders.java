package fr.eseo.twic.alidouya.server.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_no", nullable = false, referencedColumnName = "account_no")
    private Customer accountNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_status_id", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "placed_timestamp", nullable = false)
    private Instant placedTimestamp;

    @Column(name = "delivered_timestamp")
    private Instant deliveredTimestamp;

    @Column(name = "cancelled_timestamp")
    private Instant cancelledTimestamp;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Instant getCancelledTimestamp() {
        return cancelledTimestamp;
    }

    public void setCancelledTimestamp(Instant cancelledTimestamp) {
        this.cancelledTimestamp = cancelledTimestamp;
    }

    public Instant getDeliveredTimestamp() {
        return deliveredTimestamp;
    }

    public void setDeliveredTimestamp(Instant deliveredTimestamp) {
        this.deliveredTimestamp = deliveredTimestamp;
    }

    public Instant getPlacedTimestamp() {
        return placedTimestamp;
    }

    public void setPlacedTimestamp(Instant placedTimestamp) {
        this.placedTimestamp = placedTimestamp;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Customer getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Customer accountNo) {
        this.accountNo = accountNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
