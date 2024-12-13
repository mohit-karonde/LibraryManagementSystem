import React, { useEffect, useState } from 'react';
import { getAllUsers, createUser, deleteUser } from '../services/userService';
import ToastNotification from './ToastNotification'; // Import ToastNotification
import './UserManagement.css'; // Add this CSS file for styles

export const UserManagement = () => {
  const [users, setUsers] = useState([]);
  const [formData, setFormData] = useState({ firstName: '', lastName: '', email: '', phoneNumber: '' });
  const [toastMessage, setToastMessage] = useState('');
  const [showToast, setShowToast] = useState(false);

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const response = await getAllUsers();
      const users = response.data?.data || [];
      setUsers(users);
    } catch (error) {
      console.error('Failed to fetch users:', error);
      showError('Failed to fetch users.');
    }
  };

  const handleCreateUser = async () => {
    try {
      // Ensuring phone number is sent as a string (if necessary)
      const updatedFormData = {
        ...formData,
        phone: formData.phoneNumber.trim() ? formData.phoneNumber.toString() : ''  // Ensuring phone is a string
      };

      await createUser(updatedFormData);
      fetchUsers();
      setFormData({ firstName: '', lastName: '', email: '', phoneNumber: '' });
      setToastMessage('User created successfully!');
      setShowToast(true);
      setTimeout(() => setShowToast(false), 3000);
    } catch (error) {
      showError('Failed to create user.');
    }
  };

  const handleDeleteUser = async (id) => {
    try {
      await deleteUser(id);
      fetchUsers();
    } catch (error) {
      showError('Failed to delete user.');
    }
  };

  const showError = (message) => {
    setToastMessage(message);
    setShowToast(true);
    setTimeout(() => setShowToast(false), 3000);
  };

  return (
    <div className="user-management-container">
      <h2>User Management</h2>
      <div className="form-container">
        <input
          type="text"
          placeholder="First Name"
          value={formData.firstName}
          onChange={(e) => setFormData({ ...formData, firstName: e.target.value })}
        />
        <input
          type="text"
          placeholder="Last Name"
          value={formData.lastName}
          onChange={(e) => setFormData({ ...formData, lastName: e.target.value })}
        />
        <input
          type="email"
          placeholder="Email"
          value={formData.email}
          onChange={(e) => setFormData({ ...formData, email: e.target.value })}
        />
        <input
          type="text"
          placeholder="Phone"
          value={formData.phoneNumber}
          onChange={(e) => setFormData({ ...formData, phoneNumber: e.target.value })}
        />
        <button className="primary-button" onClick={handleCreateUser}>
          Create User
        </button>
      </div>

      <table className="user-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>{user.firstName} {user.lastName || ''}</td>
              <td>{user.email}</td>
              <td>{user.phoneNumber ? user.phoneNumber : '-'}</td> {/* Ensuring phoneNumber is rendered properly */}
              <td>
                <button 
                  className="delete-button"
                  onClick={(e) => { 
                    e.stopPropagation(); 
                    handleDeleteUser(user.id); 
                  }}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {showToast && <ToastNotification message={toastMessage} show={showToast} />}
    </div>
  );
};

export default UserManagement;


