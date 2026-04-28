import React, { useEffect, useState } from "react";

type ToastProps = {
  message: string;
  showToast: boolean;
  onClose: () => void;
  progress: number; // Add progress percentage
  remaining: string; // Add remaining time
};

const Toast: React.FC<ToastProps> = ({
  message,
  showToast,
  onClose,
  progress,
  remaining,
}) => {
  if (!showToast) {
    return null;
  }

  return (
    <div className="fixed top-5 right-5 w-52 min-w-[360px] p-3 bg-gray-800 text-white rounded shadow-lg z-50 flex items-center">
      <span className="flex-1">
        {message}
        <div>
          {/* Progress Bar */}
          <div className="h-2 bg-gray-600 mt-2 rounded">
            <div
              className="h-full bg-green-500 rounded"
              style={{ width: `${progress}%` }}
            ></div>
          </div>
          <div className="text-sm mt-1">{remaining}</div>
        </div>
      </span>
      {/* Close Button */}
      <button
        onClick={onClose}
        className="ml-3 text-white hover:text-gray-400"
        aria-label="Close toast"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          className="w-5 h-5"
          fill="none"
          stroke="currentColor"
          viewBox="0 0 24 24"
          strokeWidth="2"
          aria-hidden="true"
        >
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            d="M6 18L18 6M6 6l12 12"
          />
        </svg>
      </button>
    </div>
  );
};

export default Toast;
