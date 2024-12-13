import React, { useState } from 'react';
import { lendBook, returnBook, getLendingDetails } from '../services/lendingService';
import './LendingManagement.css';

const LendingManagement = ({ onBookUpdate }) => {
  const [bookId, setBookId] = useState('');
  const [userId, setUserId] = useState('');
  const [message, setMessage] = useState('');
  const [lendingDetails, setLendingDetails] = useState([]);

  // Handle lending book
  const handleLendBook = async () => {
    try {
      const response = await lendBook(bookId, userId);
      if (response.status === 'success') {
        
        setMessage('Book lent successfully');
        onBookUpdate(); // Notify parent to refresh books
        fetchLendingDetails(bookId);
      
        
       
      } else {
        setMessage(response.data.message || 'Failed to lend book');
      }
    } catch (error) {
      setMessage(error.response?.data?.message || 'Failed to lend book');
    }
  };

  // Handle returning book
  const handleReturnBook = async () => {
    try {
      const response = await returnBook(bookId, userId);
      if (response.status === 'success') {
        setMessage('Book returned successfully');
        onBookUpdate(); // Notify parent to refresh books
        // Fetch lending details again after return
        fetchLendingDetails(bookId);
      } else {
        setMessage(response.data.message || 'Failed to return book');
      }
    } catch (error) {
      setMessage(error.response?.data?.message || 'Failed to return book');
    }
  };

  // Fetch lending details for a specific book and sort by id in descending order
  const fetchLendingDetails = async () => {
    try {
      const response = await getLendingDetails(bookId);
      // Sort by id in descending order
      const sortedDetails = response.data.data.sort((a, b) => b.id - a.id);
      setLendingDetails(sortedDetails);
    } catch (error) {
      setMessage('Failed to fetch lending details');
    }
  };

  return (
    <div className="lending-management-container">
      <h2>Lending Management</h2>
      <div className="form-container">
        <input
          type="text"
          placeholder="Book ID"
          value={bookId}
          onChange={(e) => setBookId(e.target.value)}
        />
        <input
          type="text"
          placeholder="User ID"
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
        />
        <button onClick={handleLendBook}>Lend Book</button>
        <button onClick={handleReturnBook}>Return Book</button>
        <button onClick={fetchLendingDetails}>View Lending Details</button>
      </div>

      {/* Message display */}
      {message && <div className="message">{message}</div>}

      {/* Lending details table */}
      <table className="lending-table">
        <thead>
          <tr>
            <th>ID</th> {/* Display ID */}
            <th>Book ID</th>
            <th>User ID</th>
            <th>Loan Date</th>
            <th>Due Date</th>
            <th>Return Date</th>
            <th>Fine Amount</th>
          </tr>
        </thead>
        <tbody>
          {lendingDetails.map((record) => (
            <tr key={record.id}>
              <td>{record.id}</td> {/* Display ID */}
              <td>{record.book.id}</td>
              <td>{record.user.id}</td>
              <td>{record.loanDate}</td>
              <td>{record.dueDate}</td>
              <td>{record.returnDate || 'Not Returned'}</td>
              <td>{record.fineAmount || 0}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default LendingManagement;
