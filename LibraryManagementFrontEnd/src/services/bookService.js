import axios from 'axios';

const API_URL = 'http://localhost:8080/api/Book';

// Function to create a book
export const createBook = (book) => axios.post(`${API_URL}`, book);

// Function to get all books
export const getAllBooks = () => axios.get(`${API_URL}`);

// Function to get a book by ID
export const getBookById = (id) => axios.get(`${API_URL}/${id}`);

// Function to update a book by ID
export const updateBookById = (id, book) => axios.put(`${API_URL}/${id}`, book);

// Function to delete a book by ID
export const deleteBookById = (id) => axios.delete(`${API_URL}/${id}`);

// Function to find books by name
export const findBooksByName = (name) => axios.get(`${API_URL}/${name}`);

// Function to get all available books for lending
export const getAllAvailableBooksForLending = () => axios.get(`${API_URL}Available`);
