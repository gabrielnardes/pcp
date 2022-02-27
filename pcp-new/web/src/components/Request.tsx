import { useState } from 'react';
import { findProduto } from '../services/ProdutoService';

export function Request() {
    const [response, setResponse] = useState("");

    const getProduto = async () => {
        var data = await findProduto()
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