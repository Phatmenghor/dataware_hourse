// ErrorDialog.tsx

import React from "react";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog"; // Adjust the import path as needed

interface ErrorDialogProps {
  isOpen: boolean;
  totalRecard: number;
  onClose: () => void;
}

const ErrorDialog: React.FC<ErrorDialogProps> = ({
  isOpen,
  onClose,
  totalRecard,
}) => {
  return (
    <Dialog open={isOpen} onOpenChange={(open) => !open && onClose()}>
      <DialogTrigger />
      <DialogContent className="w-full max-w-lg p-6 bg-white rounded-lg shadow-xl">
        <DialogHeader className="flex items-center space-x-3">
          <DialogTitle className="text-xl font-bold text-red-500">
            Total record {totalRecard}
          </DialogTitle>
        </DialogHeader>
        <DialogDescription className="text-gray-700 mt-4">
          <p>
            The total number of records exceeds the allowed limit of 300,000
            records. You cannot view the data directly, but you can still
            generate an Excel report.
          </p>
        </DialogDescription>
        <div className="mt-4 flex justify-end">
          <button
            onClick={onClose}
            className="px-6 bg-red-600 text-white rounded-md hover:bg-red-700 h-8"
          >
            Close
          </button>
        </div>
      </DialogContent>
    </Dialog>
  );
};

export default ErrorDialog;
