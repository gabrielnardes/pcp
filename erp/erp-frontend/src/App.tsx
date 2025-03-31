import React from "react";
import { BrowserRouter as Router, Routes, Route, useNavigate } from "react-router-dom";
import ProductCRUD from "./ProductCRUD";
import Home from "./Home";
import Orders from "./Orders";
import { Button } from "@/components/ui/button";

const NavigationButtons: React.FC = () => {
  const navigate = useNavigate();

  return (
    <div className="flex gap-4 mb-4 p-2 bg-gray-200">
      <Button onClick={() => navigate("/")}>Home</Button>
      <Button onClick={() => navigate("/products")}>Products</Button>
      <Button onClick={() => navigate("/orders")}>Orders</Button>
    </div>
  );
};

const AppRoutes: React.FC = () => {
  return (
    <Router>
      <div className="p-4 max-w-lg mx-auto">
        <NavigationButtons />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/products" element={<ProductCRUD />} />
          <Route path="/orders" element={<Orders />} />
        </Routes>
      </div>
    </Router>
  );
};

export default AppRoutes;
