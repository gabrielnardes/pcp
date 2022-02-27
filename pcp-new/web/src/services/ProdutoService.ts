import { get } from '../core/Http'

export const findProduto = async () => {
    return await get('http://localhost:8080/api/v1/produto/test');
}