import React, { useState, useEffect } from 'react';
import { getAllBooks, getBookById, findByName, getAllAvailableBooks } from '../services/bookApiServices';
import './BookApiPage.css';

const BookApiPage = () => {
  const [allBooks, setAllBooks] = useState([]);
  const [bookById, setBookById] = useState(null);
  const [booksByName, setBooksByName] = useState([]);
  const [availableBooks, setAvailableBooks] = useState([]);
  const [bookId, setBookId] = useState('');
  const [bookName, setBookName] = useState('');
  const [toastMessage, setToastMessage] = useState('');
  const [showToast, setShowToast] = useState(false);

  useEffect(() => {
    fetchAllBooks();
    fetchAvailableBooks();
  }, []);

  const fetchAllBooks = async () => {
    try {
      const response = await getAllBooks();
      setAllBooks(response.data?.data || []);
    } catch (error) {
      showError('Failed to fetch all books.');
    }
  };

  const fetchBookById = async () => {
    try {
      const response = await getBookById(bookId);
      setBookById(response.data?.data || null);
    } catch (error) {
      showError('Failed to fetch book by ID.');
    }
  };

  const fetchBooksByName = async () => {
    try {
      const response = await findByName(bookName);
      setBooksByName(response.data?.data || []);
    } catch (error) {
      showError('Failed to fetch books by name.');
    }
  };

  const fetchAvailableBooks = async () => {
    try {
      const response = await getAllAvailableBooks();
      setAvailableBooks(response.data?.data || []);
    } catch (error) {
      showError('Failed to fetch available books.');
    }
  };

  const showError = (message) => {
    setToastMessage(message);
    setShowToast(true);
    setTimeout(() => setShowToast(false), 3000);
  };

  return (
    <div className="book-api-page">
      <h2>Book API Management</h2>

      {/* All Books */}
      <div>
        <h3>All Books</h3>
        <button onClick={fetchAllBooks}>Refresh All Books</button>
        <ul>
          {allBooks.map((book) => (
            <li key={book.id}>{book.bookName} - {book.author}</li>
          ))}
        </ul>
      </div>

      {/* Book by ID */}
      <div>
        <h3>Get Book by ID</h3>
        <input
          type="text"
          placeholder="Enter Book ID"
          value={bookId}
          onChange={(e) => setBookId(e.target.value)}
        />
        <button onClick={fetchBookById}>Fetch Book</button>
        {bookById && (
          <div>
            <p><strong>{bookById.bookName}</strong> - {bookById.author}</p>
            <p>{bookById.description}</p>
          </div>
        )}
      </div>

      {/* Books by Name */}
      <div>
        <h3>Find Books by Name</h3>
        <input
          type="text"
          placeholder="Enter Book Name"
          value={bookName}
          onChange={(e) => setBookName(e.target.value)}
        />
        <button onClick={fetchBooksByName}>Search</button>
        <ul>
          {booksByName.map((book) => (
            <li key={book.id}>{book.bookName} - {book.author}</li>
          ))}
        </ul>
      </div>

      {/* Available Books */}
      <div>
        <h3>Available Books for Lending</h3>
        <button onClick={fetchAvailableBooks}>Refresh Available Books</button>
        <ul>
          {availableBooks.map((book) => (
            <li key={book.id}>{book.bookName} - {book.author}</li>
          ))}
        </ul>
      </div>

      {/* Toast Notification */}
      {showToast && <div className="toast">{toastMessage}</div>}
    </div>
  );
};

export default BookApiPage;
