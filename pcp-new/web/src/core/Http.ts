import axios from 'axios'

export const get = async (url: string) =>  {
    return await axios.get(url)
                    .then((response) => response.data)
}

export const post = async (url: string, data: any) =>  {
    return await axios.post(url, data)
                    .then((response) => response.data)
}