package com.gabrielnardes.erp.order;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Status status;
    private Long quantity;
    private BigDecimal price;
    private Date creationDate;
    private Long customerId;
    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;
        return Objects.equals(id, order.id) && status == order.status && Objects.equals(quantity, order.quantity) && Objects.equals(price, order.price) && Objects.equals(creationDate, order.creationDate) && Objects.equals(customerId, order.customerId) && Objects.equals(productId, order.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, quantity, price, creationDate, customerId, productId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", quantity=" + quantity +
                ", price=" + price +
                ", creationDate=" + creationDate +
                ", customerId=" + customerId +
                ", productId=" + productId +
                '}';
    }

}
