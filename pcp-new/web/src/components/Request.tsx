import { useState } from 'react';
import { findAllProducts } from '../services/ProductService';

export function Request() {
    const [response, setResponse] = useState("");

    const getProduct = async () => {
        var data = await findAllProducts()
        setResponse(data);
    }

    return (
        <div>
            <p>Server: { response }</p>
            <button onClick={getProduct}>
                Click me
            </button>
        </div>
    );
}