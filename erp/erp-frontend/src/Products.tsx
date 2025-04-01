import React, { useState, useEffect } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Link } from "react-router-dom";

const API_HOST = "http://localhost:8080/product";

interface Product {
  id: number;
  name: string;
  price: number;
}

const Products: React.FC = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [form, setForm] = useState<{ id?: number; name: string; price: number }>({ name: "", price: 0 });

  useEffect(() => {
    fetch(`${API_HOST}`)
      .then(response => response.json())
      .then(data => setProducts(data))
      .catch(error => console.error("Error fetching products:", error));
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = () => {
    const method = form.id ? "PUT" : "POST";
    const url = form.id ? `${API_HOST}/${form.id}` : API_HOST;

    fetch(url, {
      method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    })
      .then(response => response.json())
      .then(data => {
        if (form.id) {
          setProducts(products.map(p => (p.id === form.id ? data : p)));
        } else {
          setProducts([...products, data]);
        }
        setForm({ name: "", price: 0 });
      })
      .catch(error => console.error("Error saving product:", error));
  };

  const handleEdit = (product: Product) => {
    setForm(product);
  };

  const handleDelete = (id: number) => {
    fetch(`${API_HOST}/${id}`, { method: "DELETE" })
      .then(() => {
        setProducts(products.filter(p => p.id !== id));
      })
      .catch(error => console.error("Error deleting product:", error));
  };

  return (
    <div className="p-4 mx-auto">
      <h1 className="text-xl font-bold mb-4">Product Management</h1>
      <div className="flex gap-2 mb-4">
        <Input placeholder="Product Name" name="name" value={form.name} onChange={handleChange} />
        <Input type="number" placeholder="Price" name="price" value={form.price} onChange={handleChange} />
        <Button onClick={handleSubmit}>{form.id !== undefined ? "Update" : "Add"}</Button>
      </div>
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>Name</TableHead>
            <TableHead>Price</TableHead>
            <TableHead>Actions</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {products.map((product) => (
            <TableRow key={product.id}>
              <TableCell>{product.id}</TableCell>
              <TableCell>{product.name}</TableCell>
              <TableCell>${product.price}</TableCell>
              <TableCell>
                <Button size="sm" onClick={() => handleEdit(product)}>Edit</Button>
                <Button size="sm" variant="destructive" onClick={() => handleDelete(product.id)}>Delete</Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default Products;
