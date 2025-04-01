package com.gabrielnardes.erp.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class OrderDTOResponse implements Serializable {

    private Long id;
    private Status status;
    private Long quantity;
    private BigDecimal price;
    private BigDecimal total;
    private Date creationDate;
    private String customer;
    private String product;

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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderDTOResponse that)) return false;
        return Objects.equals(id, that.id) && status == that.status && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price) && Objects.equals(total, that.total) && Objects.equals(creationDate, that.creationDate) && Objects.equals(customer, that.customer) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, quantity, price, total, creationDate, customer, product);
    }

    @Override
    public String toString() {
        return "OrderDTOResponse{" +
                "id=" + id +
                ", status=" + status +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                ", creationDate=" + creationDate +
                ", customer='" + customer + '\'' +
                ", product='" + product + '\'' +
                '}';
    }
}
