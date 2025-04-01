package com.gabrielnardes.erp.order;

import java.io.Serializable;
import java.util.Objects;

public class OrderDTO implements Serializable {

    private Long quantity;
    private Long customerId;
    private Long productId;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
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
        if (!(o instanceof OrderDTO orderDTO)) return false;
        return Objects.equals(quantity, orderDTO.quantity) && Objects.equals(customerId, orderDTO.customerId) && Objects.equals(productId, orderDTO.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, customerId, productId);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "quantity=" + quantity +
                ", customerId='" + customerId + '\'' +
                ", productId=" + productId +
                '}';
    }
}
