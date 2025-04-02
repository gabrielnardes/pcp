package com.gabrielnardes.erp.order;

import java.io.Serializable;
import java.util.Objects;

public class OrderDTO implements Serializable {

    private Long quantity;
    private Long customerId;
    private Long productId;
    private Long originId;
    private Long destinationId;

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

    public Long getOriginId() {
        return originId;
    }

    public void setOriginId(Long originId) {
        this.originId = originId;
    }

    public Long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderDTO orderDTO)) return false;
        return Objects.equals(quantity, orderDTO.quantity) && Objects.equals(customerId, orderDTO.customerId) && Objects.equals(productId, orderDTO.productId) && Objects.equals(originId, orderDTO.originId) && Objects.equals(destinationId, orderDTO.destinationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, customerId, productId, originId, destinationId);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "quantity=" + quantity +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", originId=" + originId +
                ", destinationId=" + destinationId +
                '}';
    }

}
