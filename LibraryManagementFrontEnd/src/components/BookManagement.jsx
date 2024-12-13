import React, { useState, useEffect } from 'react';
import {
  createBook,
  getAllBooks,
  deleteBookById,
} from '../services/bookService';
import './BookManagement.css';

const BookManagement = () => {
  const [books, setBooks] = useState([]);
  const [formData, setFormData] = useState({ bookName: '', description: '', isAvailable: true, author: '', stock: 10 });
  const [toastMessage, setToastMessage] = useState('');
  const [showToast, setShowToast] = useState(false);

  useEffect(() => {
    fetchBooks();
  }, []);

  const fetchBooks = async () => {
    try {
      const response = await getAllBooks();
      const validBooks = response.data?.data.filter((book) => book.bookName && book.description) || [];
      setBooks(validBooks);
    } catch (error) {
      showError('Failed to fetch books.');
    }
  };

  const handleCreateBook = async () => {
    try {
      await createBook(formData);
      fetchBooks();
      setFormData({ bookName: '', description: '', isAvailable: true, author: '', stock: 10 });
      showSuccess('Book created successfully!');
    } catch (error) {
      showError('Failed to create book.');
    }
  };

  const handleDeleteBook = async (id) => {
    try {
      await deleteBookById(id);
      fetchBooks();
      showSuccess('Book deleted successfully!');
    } catch (error) {
      showError('Failed to delete book.');
    }
  };

  const showError = (message) => {
    setToastMessage(message);
    setShowToast(true);
    setTimeout(() => setShowToast(false), 3000);
  };

  const showSuccess = (message) => {
    setToastMessage(message);
    setShowToast(true);
    setTimeout(() => setShowToast(false), 3000);
  };

  return (
    <div className="book-management-container">
      <h2>Book Management</h2>

      {/* Book Form */}
      <div className="form-container">
        <input
          type="text"
          placeholder="Book Name"
          value={formData.bookName}
          onChange={(e) => setFormData({ ...formData, bookName: e.target.value })}
        />
        <input
          type="text"
          placeholder="Description"
          value={formData.description}
          onChange={(e) => setFormData({ ...formData, description: e.target.value })}
        />
        <input
          type="text"
          placeholder="Author"
          value={formData.author}
          onChange={(e) => setFormData({ ...formData, author: e.target.value })}
        />
        <label>
          <input
            type="checkbox"
            checked={formData.isAvailable}
            onChange={(e) => setFormData({ ...formData, isAvailable: e.target.checked })}
          />
          Available
        </label>
        <input
          type="number"
          placeholder="Stock"
          value={formData.stock}
          onChange={(e) => setFormData({ ...formData, stock: e.target.value })}
        />
        <button className="primary-button" onClick={handleCreateBook}>
          Add Book
        </button>
      </div>

      {/* Book List */}
      <table className="book-table">
        <thead>
          <tr>
            <th>BookId</th>
            <th>Name</th>
            <th>Description</th>
            <th>Author</th>
            <th>Available</th>
            <th>Stock</th> {/* New column for stock */}
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {books.map((book) => (
            <tr key={book.id}>
              <td>{book.id}</td>
              <td>{book.bookName}</td>
              <td>{book.description}</td>
              <td>{book.author}</td>
              <td>{book.isAvailable ? 'Yes' : 'No'}</td>
              <td>{book.stock !== null ? book.stock : 'Not Available'}</td> {/* Display stock or 'Not Available' */}
              <td>
                <button
                  className="delete-button"
                  onClick={() => handleDeleteBook(book.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Toast Notification */}
      {showToast && <div className="toast">{toastMessage}</div>}
    </div>
  );
};

export default BookManagement;
