import React from 'react';

const ToastNotification = ({ message, show }) => {
  if (!show) return null;

  return (
    <div className="toast">
      <div className="toast-message">{message}</div>
    </div>
  );
};

export default ToastNotification;
