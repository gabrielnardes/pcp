import { NavLink, Route, Routes } from 'react-router-dom';
import './App.css';

function App() {
  return (
    <div>
      <NavLink to='/products'>Produtos</NavLink> <br />
      <NavLink to='/users'>Usuários</NavLink>
      <main>
        <Routes>
          <Route path='/products' element={<Products />} />
          <Route path='/users' element={<Users />} />
        </Routes>
      </main>
    </div>
  );
}

function Products() {
  return <h2>Products</h2>;
}

function Users() {
  return <h2>Users</h2>;
}

export default App;
