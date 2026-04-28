"use client";

import React, { useEffect, useState } from "react";
import { DepartmentColumn } from "./columns";
import { Button } from "@/components/ui/button";
import { Plus } from "lucide-react";
import { useUnitModal } from "@/hooks/use-unit-modal";
import axios from "axios";
import { units } from "@prisma/client";

interface UnitActionProps {
  data: DepartmentColumn;
}

export const UnitAction: React.FC<UnitActionProps> = ({ data }) => {
  const unitModal = useUnitModal();

  return (
    <div>
      <Button
        type="button"
        size={"sm"}
        className="flex items-center text-gray-600 h-7 shadow-none rounded-md bg-gray-200 hover:bg-gray-300 hover:text-blue-600"
        onClick={() => unitModal.onOpen(data.id, data.units)}
      >
        <span className="text-xs">Units ({data.units?.length ?? "0"})</span>
        <Plus className="w-3 h-3 ml-2" />
      </Button>
    </div>
  );
};
