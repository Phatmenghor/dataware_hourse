"use client";

import { ColumnDef } from "@tanstack/react-table";
import { CellAction } from "./cell-action";
import { UnitAction } from "./unit-action";
import { units } from "@prisma/client";
import { Button } from "@/components/ui/button";
import { ArrowUpDown } from "lucide-react";

export type DepartmentColumn = {
  id: string;
  name: string;
  short_name: string;
  code: string;
  status: false | true;
  type: string | null;
  units: units[];
};

export const columns: ColumnDef<DepartmentColumn>[] = [
  {
    id: "key",
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
    accessorKey: "short_name",
    header: "Short Name",
  },
  {
    accessorKey: "code",
    header: "Code",
  },
  {
    accessorKey: "type",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          className="text-[10px] font-medium uppercase text-gray-500 px-0"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Type
          <ArrowUpDown className="ml-1 h-3 w-3" />
        </Button>
      )
    },
    cell: ({ row }) =>
      row.original.type === "HO" ? (
        <span className="py-1 px-2 rounded-md bg-blue-200 text-blue-600 text-[10px]">
          HO
        </span>
      ) : (
        <span className="py-1 px-2 rounded-md bg-green-200 text-green-600 text-[10px]">
          Branch
        </span>
      ),
  },
  {
    id: "units",
    header: "Units",
    cell: ({ row }) => <UnitAction data={row.original} />,
  },
  {
    id: "action",
    header: "Action",
    cell: ({ row }) => <CellAction data={row.original} />,
  },
];
