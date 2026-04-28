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

const ErrorDialogReport: React.FC<ErrorDialogProps> = ({
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
            The total number of records exceeds the allowed limit of 5,000,000
            records. As a result, exporting the data to Excel is not possible.
            Please consider applying filters to narrow down the data.
            Alternatively, you can export the data in smaller chunks. If you
            need further assistance, please contact support.
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

export default ErrorDialogReport;
