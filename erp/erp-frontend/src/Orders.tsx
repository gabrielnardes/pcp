import React, { useState, useEffect } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";

const API_HOST = "http://localhost:8080/order";
const API_PRODUCTS = "http://localhost:8080/product";
const API_CUSTOMERS = "http://localhost:8080/customer";

interface Order {
  id: number;
  status: string;
  quantity: number;
  price: number;
  total: number;
  creationDate: Date;
  customer: string;
  product: string;
}

const Orders: React.FC = () => {
  const [orders, setOrders] = useState<Order[]>([]);
  const [products, setProducts] = useState<{ id: number; name: string }[]>([]);
  const [customers, setCustomers] = useState<{ id: number; name: string }[]>([]);
  const [form, setForm] = useState<{ quantity: number; customerId: number; productId: number }>({
    quantity: 1,
    customerId: 0,
    productId: 0,
  });

  useEffect(() => {
    fetch(API_HOST)
      .then((res) => res.json())
      .then((data) => setOrders(data))
      .catch((err) => console.error("Error fetching orders:", err));

    fetch(API_PRODUCTS)
      .then((res) => res.json())
      .then((data) => setProducts(data))
      .catch((err) => console.error("Error fetching products:", err));

    fetch(API_CUSTOMERS)
      .then((res) => res.json())
      .then((data) => setCustomers(data))
      .catch((err) => console.error("Error fetching customers:", err));
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleCreate = () => {
    console.log(form)
    fetch(API_HOST, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    })
      .then((res) => res.json())
      .then((data) => setOrders([...orders, data]))
      .catch((err) => console.error("Error creating order:", err));
  };

  const handleCancel = (id: number) => {
    fetch(`${API_HOST}/${id}`, { method: "PUT" })
      .then(response => response.json())
      .then((data) => setOrders(data))
      .catch((err) => console.error("Error canceling order:", err));
  };

  return (
    <div className="p-4 max-w-lg mx-auto">
      <h1 className="text-xl font-bold mb-4">Order Management</h1>
      <div className="flex gap-2 mb-4">
        <Input type="number" name="quantity" placeholder="Quantity" value={form.quantity} onChange={handleChange} />
        <select name="customerId" value={form.customerId} onChange={handleChange} className="border p-2">
          <option value={0}>Customer</option>
          {customers.map((customer) => (
            <option key={customer.id} value={customer.id}>{customer.name}</option>
          ))}
        </select>
        <select name="productId" value={form.productId} onChange={handleChange} className="border p-2">
          <option value={0}>Select Product</option>
          {products.map((product) => (
            <option key={product.id} value={product.id}>{product.name}</option>
          ))}
        </select>
        <Button onClick={handleCreate}>Create Order</Button>
      </div>
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>Status</TableHead>
            <TableHead>Quantity</TableHead>
            <TableHead>Price</TableHead>
            <TableHead>Total</TableHead>
            <TableHead>Created At</TableHead>
            <TableHead>Customer</TableHead>
            <TableHead>Product</TableHead>
            <TableHead>Actions</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {orders.map((order) => (
            <TableRow key={order.id}>
              <TableCell>{order.id}</TableCell>
              <TableCell>{order.status}</TableCell>
              <TableCell>{order.quantity}</TableCell>
              <TableCell>${order.price == null ? null : order.price.toFixed(2)}</TableCell>
              <TableCell>${order.total == null ? null : order.total.toFixed(2)}</TableCell>
              <TableCell>{new Date(order.creationDate).toLocaleString()}</TableCell>
              <TableCell>{order.customer}</TableCell>
              <TableCell>{order.product}</TableCell>
              <TableCell>
                <Button size="sm" variant="destructive" onClick={() => handleCancel(order.id)}>
                  Cancel
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default Orders;
