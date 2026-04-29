import apiClient from "@/lib/api-client";
import { Report } from "@/types/report";

export const reportsAPI = {
  async getAllReports(): Promise<Report[]> {
    try {
      const response = await apiClient.get("/reports");
      return response.data || [];
    } catch (error: any) {
      console.error("Error fetching reports:", error.message);
      throw error;
    }
  },

  async getReportById(id: string): Promise<Report> {
    try {
      const response = await apiClient.get(`/reports/${id}`);
      return response.data;
    } catch (error: any) {
      console.error("Error fetching report:", error.message);
      throw error;
    }
  },

  async getReportByDepartment(departmentId: string): Promise<Report[]> {
    try {
      const response = await apiClient.get(`/reports/department/${departmentId}`);
      return response.data || [];
    } catch (error: any) {
      console.error("Error fetching reports by department:", error.message);
      throw error;
    }
  },

  async deleteReport(id: string): Promise<void> {
    try {
      await apiClient.delete(`/reports/${id}`);
    } catch (error: any) {
      console.error("Error deleting report:", error.message);
      throw error;
    }
  },
};
