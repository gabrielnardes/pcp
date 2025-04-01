import React, { useState, useEffect } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";

const API_HOST = "http://localhost:8080/customer";

interface Customer {
  id: number;
  name: string;
  email: string;
  phoneNumber: string;
  address: string;
}

const Customers: React.FC = () => {
  const [customers, setCustomers] = useState<Customer[]>([]);
  const [form, setForm] = useState<{ name: string; email: string; phoneNumber: string; address: string }>({
    name: "",
    email: "",
    phoneNumber: "",
    address: "",
  });

  useEffect(() => {
    fetch(API_HOST)
      .then((res) => res.json())
      .then((data) => setCustomers(data))
      .catch((err) => console.error("Error fetching customers:", err));
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = () => {
    const method = form.id ? "PUT" : "POST";
    const url = form.id ? `${API_HOST}/${form.id}` : API_HOST;

    fetch(API_HOST, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    })
      .then((res) => res.json())
      .then(data => {
        if (form.id) {
          setCustomers(customers.map(c => (c.id === form.id ? data : c)));
        } else {
          setCustomers([...customers, data]);
        }
        setForm({ name: "", email: "", phoneNumber: "", address: "" });
      })
      .catch((err) => console.error("Error creating customer:", err));
  };

  const handleEdit = (customer: Customer) => {
    setForm(customer);
  };

  const handleDelete = (id: number) => {
    fetch(`${API_HOST}/${id}`, { method: "DELETE" })
      .then(() => {
        setCustomers(customers.filter(c => c.id !== id));
      })
      .catch(error => console.error("Error deleting customer:", error));
  };

  return (
    <div className="p-4 max-w-lg mx-auto">
      <h1 className="text-xl font-bold mb-4">Customer Management</h1>
      <div className="flex gap-2 mb-4">
        <Input type="text" name="name" placeholder="Name" value={form.name} onChange={handleChange} />
        <Input type="text" name="email" placeholder="Email" value={form.email} onChange={handleChange} />
        <Input type="text" name="phoneNumber" placeholder="Phone Number" value={form.phoneNumber} onChange={handleChange} />
        <Input type="text" name="address" placeholder="Address" value={form.address} onChange={handleChange} />
        <Button onClick={handleSubmit}>{form.id !== undefined ? "Update" : "Add"}</Button>
      </div>
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>Name</TableHead>
            <TableHead>Email</TableHead>
            <TableHead>Phone Number</TableHead>
            <TableHead>Address</TableHead>
            <TableHead>Actions</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {customers.map((customer) => (
            <TableRow key={customer.id}>
              <TableCell>{customer.id}</TableCell>
              <TableCell>{customer.name}</TableCell>
              <TableCell>{customer.email}</TableCell>
              <TableCell>{customer.phoneNumber}</TableCell>
              <TableCell>{customer.address}</TableCell>
              <TableCell>
                <Button size="sm" onClick={() => handleEdit(customer)}>Edit</Button>
                <Button size="sm" variant="destructive" onClick={() => handleDelete(customer.id)}>
                  Delete
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default Customers;
