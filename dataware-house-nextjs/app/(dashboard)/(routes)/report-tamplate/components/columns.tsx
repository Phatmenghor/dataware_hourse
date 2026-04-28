"use client";

import { ColumnDef } from "@tanstack/react-table";
import { CellAction } from "./cell-action";
import { CellStatus } from "./cell-status";
import { CellMis } from "./cell-mis";
import { Button } from "@/components/ui/button";
import { ArrowUpDown } from "lucide-react";

export type ReportColumn = {
  id: string;
  name: string;
  status: false | true;
  mis: false | true;
  department: string;
  unit: string;
  department_id:string;
  unit_id:string;
};

export const columns: ColumnDef<ReportColumn>[] = [
  {
    id: "No",
    header: "#",
    cell: ({ row }) => <span>{row.index + 1}</span>,
  },
  {
    accessorKey: "name",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          className="text-[10px] font-medium uppercase text-gray-500 px-0"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Name
          <ArrowUpDown className="ml-1 h-3 w-3" />
        </Button>
      )
    },
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
    accessorKey: "unit",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          className="text-[10px] font-medium uppercase text-gray-500 px-0"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Unit
          <ArrowUpDown className="ml-1 h-3 w-3" />
        </Button>
      )
    },
  },
  {
    id: "status",
    header: "Status",
    cell: ({ row }) => <CellStatus data={row.original} />,
  },
  {
    id: "mis",
    header: "MIS",
    cell: ({ row }) => <CellMis data={row.original} />,
  },
  {
    id: "action",
    header: "Action",
    cell: ({ row }) => <CellAction data={row.original} />,
  },
];
