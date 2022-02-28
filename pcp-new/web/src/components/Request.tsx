import { useState } from 'react';
import { findAllProducts } from '../services/ProdutoService';

export function Request() {
    const [response, setResponse] = useState("");

    const getProduto = async () => {
        var data = await findAllProducts()
        setResponse(data);
    }

    return (
        <div>
            <p>Server: { response }</p>
            <button onClick={getProduto}>
                Click me
            </button>
        </div>
    );
}