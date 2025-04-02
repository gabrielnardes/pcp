import React from "react";
import { BrowserRouter as Router, Routes, Route, useNavigate } from "react-router-dom";
import Products from "./Products";
import Home from "./Home";
import Orders from "./Orders";
import Customers from "./Customers";
import Locations from "./Locations";
import { Button } from "@/components/ui/button";

const NavigationButtons: React.FC = () => {
  const navigate = useNavigate();

  return (
    <div className="flex gap-4 mb-4 p-2 bg-gray-200">
      <Button onClick={() => navigate("/")}>Home</Button>
      <Button onClick={() => navigate("/products")}>Products</Button>
      <Button onClick={() => navigate("/customers")}>Customers</Button>
      <Button onClick={() => navigate("/locations")}>Locations</Button>
      <Button onClick={() => navigate("/orders")}>Orders</Button>
    </div>
  );
};

const AppRoutes: React.FC = () => {
  return (
    <Router>
      <div className="p-4 max-w-200 mx-auto">
        <NavigationButtons />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/products" element={<Products />} />
          <Route path="/customers" element={<Customers />} />
          <Route path="/locations" element={<Locations />} />
          <Route path="/orders" element={<Orders />} />
        </Routes>
      </div>
    </Router>
  );
};

export default AppRoutes;
