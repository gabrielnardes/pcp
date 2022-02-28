import { get } from '../core/Http'

export const findAllProducts = async () => {
    return await get('http://localhost:8080/api/v1/product');
}