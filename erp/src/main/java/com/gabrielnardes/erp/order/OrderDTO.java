package com.gabrielnardes.erp.order;

import java.io.Serializable;
import java.util.Objects;

public class OrderDTO implements Serializable {

    private Long quantity;
    private String client;
    private Long productId;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
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
        return Objects.equals(quantity, orderDTO.quantity) && Objects.equals(client, orderDTO.client) && Objects.equals(productId, orderDTO.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, client, productId);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "quantity=" + quantity +
                ", client='" + client + '\'' +
                ", product=" + productId +
                '}';
    }
}
