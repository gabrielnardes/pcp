import { Product } from '../App';
import { get, post } from '../core/Http'

export const findAllProducts = async () => {
    return await get('http://localhost:8080/api/v1/product');
}

export const saveProduct = async (product: Product) => {
    return await post('http://localhost:8080/api/v1/product', product);
}