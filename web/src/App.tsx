import { useEffect, useState } from 'react';
import { Link, NavLink, Outlet, Route, Routes } from 'react-router-dom';
import './App.css';
import { findAllProducts, saveProduct as saveProductService } from './services/ProductService';
import { useForm, SubmitHandler } from "react-hook-form";

function App() {
  return (
    <div>
      <NavLink to='/products'>Products</NavLink> <br />
      <NavLink to='/users'>Users</NavLink>
      <main>
        <Routes>
          <Route path='/products/*' element={<ProductsMenu />}>
            <Route path='new' element={<NewProduct />} />
            <Route path='list' element={<AllProducts />} />
          </Route>
          <Route path='/users' element={<Users />} />
        </Routes>
      </main>
    </div>
  );
}

const AllProducts = () => {
  const [response, setResponse] = useState<Product[]>([]);

  useEffect(() => {
    getProduct();
  }, []);

  const getProduct = async () => {
    var data = await findAllProducts()
    setResponse(data);
  }

  return (
    <div className="container">
      <h2>Simple Inventory Table</h2>
      <table>
        <thead>
          <tr>
            <th>Product Name</th>
            <th>Unit Price</th>
          </tr>
        </thead>
        <tbody>
          {
            response.map((item) => (
              <tr key={item.id}>
                <td>{item.name}</td>
                <td>{item.price}</td>
                <td />
              </tr>
            ))
          }
        </tbody>
      </table>
    </div>
  )
}

function ProductsMenu() {
  return (
    <div>
      <h2>Products</h2>
      <Link to='new'>New Product</Link> <br />
      <Link to='list'>List all products</Link>
      <Outlet />
    </div>
  )
}

function NewProduct() {
  const { register, handleSubmit } = useForm<Product>();
  const onSubmit: SubmitHandler<Product> = data => {
    saveProduct(data);
  }

  const saveProduct = async (data: Product) => {
    await saveProductService(data);
  }

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <label>Product name</label>
      <input {...register("name")} /> <br />
      <label>Product price</label>
      <input {...register("price")} /> <br />
      <input type="submit" value="Create product" />
    </form>
  )
}

function Users() {
  return <h2>Users</h2>;
}

export interface Product {
  id: number;
  name: string;
  price: number;
}

export default App;
