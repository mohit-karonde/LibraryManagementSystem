import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import WelcomePage from './components/WelcomePage';
import BookManagement from './components/BookManagement';
import LendingManagement from './components/LendingManagement';
import UserManagement from './components/UserManagement';
import BookApiPage from './components/BookApiPage';
import './App.css';

const App = () => {
  return (
    <Router>
      <div className="app-container">
        {/* Taskbar */}
        <nav className="taskbar">
          <ul>
            <li>
              <Link to="/">Welcome</Link>
            </li>
            <li>
              <Link to="/books">Book Management</Link>
            </li>
            <li>
              <Link to="/lendings">Lending Management</Link>
            </li>
            <li>
              <Link to="/users">User Management</Link>
            </li>
            <li>
              <Link to="/searchBook">SearchBooks</Link>
            </li>
          </ul>
        </nav>

        {/* Routes */}
        <Routes>
          <Route path="/" element={<WelcomePage />} />
          <Route path="/books" element={<BookManagement />} />
          <Route path="/lendings" element={<LendingManagement />} />
          <Route path="/users" element={<UserManagement />} />
          <Route path="/searchBook" element={<BookApiPage />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
