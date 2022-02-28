import { useEffect, useState } from 'react';
import { Link, NavLink, Outlet, Route, Routes } from 'react-router-dom';
import './App.css';
import { findAllProducts } from './services/ProdutoService';

function App() {
  return (
    <div>
      <NavLink to='/products'>Produtos</NavLink> <br />
      <NavLink to='/users'>Usuários</NavLink>
      <main>
        <Routes>
          <Route path='/products/*' element={<Products />}>
            <Route path='new' element={<p>Create product</p>} />
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
    getProduto();
  }, []);

  const getProduto = async () => {
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

function Products() {
  return (
    <div>
      <h2>Products</h2>
      <Link to='new'>New Product</Link> <br />
      <Link to='list'>List all products</Link>
      <Outlet />
    </div>
  )
}

function Users() {
  return <h2>Users</h2>;
}

interface Product {
  id: number;
  name: string;
  price: number;
}

export default App;
