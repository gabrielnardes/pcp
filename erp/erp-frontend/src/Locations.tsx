import React, { useEffect, useState } from "react";
import { Button } from "@/components/ui/button";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Input } from "@/components/ui/input";

const API_HOST = "http://localhost:8080/location";

interface Location {
  id: number;
  name: string;
  street: string;
  number: string;
  neighborhood: string;
  city: string;
  country: string;
  latitude: number;
  longitude: number;
  status: "ACTIVE" | "INACTIVE";
}

const Locations: React.FC = () => {
  const [locations, setLocations] = useState<Location[]>([]);
  const [form, setForm] = useState<Location>({
    name: "",
    street: "",
    number: "",
    neighborhood: "",
    city: "",
    country: "",
    latitude: 0,
    longitude: 0,
    status: "ACTIVE",
  });

  useEffect(() => {
    fetch(API_HOST)
      .then((response) => response.json())
      .then((data) => setLocations(data))
      .catch((error) => console.error("Error fetching locations:", error));
  }, []);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleEdit = (product: Location) => {
    setForm(product);
  };

  const handleCreate = () => {
    fetch(API_HOST, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    })
      .then((response) => response.json())
      .then((newLocation) => {
        setLocations([...locations, newLocation]);
        setForm({
          name: "",
          street: "",
          number: "",
          neighborhood: "",
          city: "",
          country: "",
          latitude: 0,
          longitude: 0,
          status: "ACTIVE",
        }); // Reset form after submission
      })
      .catch((error) => console.error("Error creating location:", error));
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
          setLocations(locations.map(p => (p.id === form.id ? data : p)));
        } else {
          setLocations([...locations, data]);
        }
        setForm({
          name: "",
          street: "",
          number: "",
          neighborhood: "",
          city: "",
          country: "",
          latitude: 0,
          longitude: 0,
          status: "ACTIVE",
        }); // Reset form after submission
      })
      .catch(error => console.error("Error saving location:", error));
  };

  const handleDelete = (id: number) => {
    fetch(`${API_HOST}/${id}`, { method: "DELETE" })
      .then(() => setLocations(locations.filter((loc) => loc.id !== id)))
      .catch((error) => console.error("Error deleting location:", error));
  };

  const handleToggleStatus = (id: number, currentStatus: "ACTIVE" | "INACTIVE") => {
    const newStatus = currentStatus === "ACTIVE" ? "INACTIVE" : "ACTIVE";
    fetch(`${API_HOST}/${id}/status`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ status: newStatus }),
    })
      .then((response) => response.json())
      .then((updatedLocation) => {
        setLocations(locations.map((loc) => (loc.id === id ? updatedLocation : loc)));
      })
      .catch((error) => console.error("Error updating location status:", error));
  };

  return (
    <div className="p-4 max-w-6xl mx-auto">
      <h1 className="text-xl font-bold mb-4">Locations Management</h1>

      {/* Form to create a new location */}
      <div className="flex flex-wrap gap-4 mb-4">
        <Input
          placeholder="Location Name"
          name="name"
          value={form.name}
          onChange={handleChange}
        />
        <Input
          placeholder="Street"
          name="street"
          value={form.street}
          onChange={handleChange}
        />
        <Input
          placeholder="Number"
          name="number"
          value={form.number}
          onChange={handleChange}
        />
        <Input
          placeholder="Neighborhood"
          name="neighborhood"
          value={form.neighborhood}
          onChange={handleChange}
        />
        <Input
          placeholder="City"
          name="city"
          value={form.city}
          onChange={handleChange}
        />
        <Input
          placeholder="Country"
          name="country"
          value={form.country}
          onChange={handleChange}
        />
        <Input
          placeholder="Latitude"
          name="latitude"
          value={form.latitude}
          onChange={handleChange}
          type="number"
        />
        <Input
          placeholder="Longitude"
          name="longitude"
          value={form.longitude}
          onChange={handleChange}
          type="number"
        />
        <select
          name="status"
          value={form.status}
          onChange={handleChange}
          className="border p-2"
        >
          <option value="ACTIVE">Active</option>
          <option value="INACTIVE">Inactive</option>
        </select>
        <Button onClick={handleSubmit}>{form.id !== undefined ? "Update" : "Add"}</Button>
      </div>

      <div className="overflow-x-auto">
        <Table className="table-auto w-full">
          <TableHeader>
            <TableRow>
              <TableHead>ID</TableHead>
              <TableHead>Name</TableHead>
              <TableHead>Street</TableHead>
              <TableHead>Number</TableHead>
              <TableHead>Neighborhood</TableHead>
              <TableHead>City</TableHead>
              <TableHead>Country</TableHead>
              <TableHead>Latitude</TableHead>
              <TableHead>Longitude</TableHead>
              <TableHead>Status</TableHead>
              <TableHead>Actions</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {locations.map((location) => (
              <TableRow key={location.id}>
                <TableCell>{location.id}</TableCell>
                <TableCell>{location.name}</TableCell>
                <TableCell>{location.street}</TableCell>
                <TableCell>{location.number}</TableCell>
                <TableCell>{location.neighborhood}</TableCell>
                <TableCell>{location.city}</TableCell>
                <TableCell>{location.country}</TableCell>
                <TableCell>{location.latitude}</TableCell>
                <TableCell>{location.longitude}</TableCell>
                <TableCell>{location.status}</TableCell>
                <TableCell className="flex gap-2">
                  <Button size="sm" onClick={() => handleEdit(location)}>Update</Button>
                  <Button size="sm" variant="destructive" onClick={() => handleDelete(location.id)}>Delete</Button>
                  <Button size="sm" onClick={() => handleToggleStatus(location.id, location.status)}>
                    {location.status === "ACTIVE" ? "Deactivate" : "Activate"}
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
};

export default Locations;
