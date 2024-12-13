import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api';

export const getAllBooks = () => axios.get(`${BASE_URL}/Book`);
export const getBookById = (id) => axios.get(`${BASE_URL}/Book/${id}`);
export const findByName = (name) => axios.get(`${BASE_URL}/Book/${name}`);
export const getAllAvailableBooks = () => axios.get(`${BASE_URL}/BookAvailable`);
