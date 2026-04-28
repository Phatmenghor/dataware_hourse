"use client";

import { Button } from "@/components/ui/button";
import { DataTable } from "@/components/ui/data-table";
import { Plus } from "lucide-react";
import React, { useEffect, useState } from "react";
import { ReportColumn, columns } from "./columns";
import { useReportModal } from "@/hooks/use-report-modal";
import axios from "axios";
import { departments } from "@prisma/client";

export const ReportTamplateClient = () => {
  const reportModal = useReportModal();
  const [dataFormate, setDataFormate] = useState<ReportColumn[]>([]);
  const [loading, setLoading] = useState(false);
  const [departments, setDepartments] = useState<departments[]>([]);

  const getDepartments = async () => {
    try {
      await axios.get("/api/departments").then((data) => {
        setDepartments(data.data);
      });
    } catch (error) {}
  };

  const getData = async () => {
    setLoading(true);
    await axios.get("/api/reports").then((data: any) => {
      const formattedReports: ReportColumn[] = data.data.map((item: any) => ({
        id: item.id,
        name: item.name,
        code: item.code,
        status: item.status,
        mis: item.mis,
        category: item.category,
        department: item.department ? item.department.name : "",
        unit: item.unit ? item.unit.name : "",
        department_id: item.department ? item.department.id : "0",
        unit_id: item.unit ? item.unit.id : "0",
      }));
      setDataFormate(formattedReports);
      setLoading(false);
    });
  };

  useEffect(() => {
    getDepartments();
    getData();
  }, [departments]);

  return (
    <div>
      <div className="flex justify-between items-center">
        <h1 className="text-sm font-semibold text-gray-700">Report Template</h1>

        <Button
          type="button"
          onClick={() => reportModal.onOpen()}
          className="shadow-none flex items-center cursor-pointer rounded-md py-1.5 px-3"
        >
          <Plus className="w-3 h-3 mr-1.5" />
          <span className="uppercase text-[9px]">Add New</span>
        </Button>
      </div>

      <div className="bg-white px-4 rounded-lg mt-5">
        <div className="overflow-x-auto">
          <DataTable columns={columns} data={dataFormate} searchKey="name" />
        </div>
      </div>
    </div>
  );
};
