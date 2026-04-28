"use client";

import { ColumnDef } from "@tanstack/react-table";
import { CellAction } from "./cell-action";
import { CellStatus } from "./cell-status";
import { Button } from "@/components/ui/button";
import { ArrowUpDown } from "lucide-react";

export type UserColumn = {
  id: string;
  full_name: string;
  staff_id: string;
  username: string;
  department: string;
  position: string;
  role: string;
  status: false | true;
};

export const columns: ColumnDef<UserColumn>[] = [
  {
    id: "key",
    header: "#",
    cell: ({ row }) => <span>{row.index + 1}</span>,
  },
  {
    accessorKey: "full_name",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          className="text-[10px] font-medium uppercase text-gray-500 px-0"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Full Name
          <ArrowUpDown className="ml-1 h-3 w-3" />
        </Button>
      )
    },
  },
  {
    accessorKey: "staff_id",
    header: "Staff Id",
  },
  {
    accessorKey: "username",
    header: "Username",
  },
  {
    accessorKey: "department",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          className="text-[10px] font-medium uppercase text-gray-500 px-0"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Department
          <ArrowUpDown className="ml-1 h-3 w-3" />
        </Button>
      )
    },
  },
  {
    accessorKey: "position",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          className="text-[10px] font-medium uppercase text-gray-500 px-0"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Position
          <ArrowUpDown className="ml-1 h-3 w-3" />
        </Button>
      )
    },
  },
  {
    accessorKey: "role",
    header: "Role",
  },
  {
    id: "status",
    header: "Status",
    cell: ({ row }) => <CellStatus data={row.original} />,
  },
  {
    id: "action",
    header: "Action",
    cell: ({ row }) => <CellAction data={row.original} />,
  },
];
