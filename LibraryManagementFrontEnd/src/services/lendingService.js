import axios from 'axios';
const API_URL = 'http://localhost:8080/api';

export const lendBook = (bookId, userId) => axios.post(`${API_URL}/Lend/${bookId}/${userId}`);
export const returnBook = (bookId, userId) => axios.put(`${API_URL}/Lend/${bookId}/${userId}`);
export const getLendingDetails = (bookId) => axios.get(`${API_URL}/lendBookdetail/${bookId}`);




