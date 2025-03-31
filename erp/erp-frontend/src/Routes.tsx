import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import ProductCRUD from "./ProductCRUD"; // Ensure correct path
import Home from "./Home"; // Create this component
import Orders from "./Orders"; // Create this component

const AppRoutes: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/products" element={<ProductCRUD />} />
        <Route path="/orders" element={<Orders />} />
      </Routes>
    </Router>
  );
};

export default AppRoutes;
