import apiClient from "@/lib/api-client";
import { Department, CreateDepartmentRequest, UpdateDepartmentRequest } from "@/types/department";

export const departmentsAPI = {
  async getAllDepartments(): Promise<Department[]> {
    try {
      const response = await apiClient.get("/departments");
      return response.data || [];
    } catch (error: any) {
      console.error("Error fetching departments:", error.message);
      throw error;
    }
  },

  async getDepartmentById(id: string): Promise<Department> {
    try {
      const response = await apiClient.get(`/departments/${id}`);
      return response.data;
    } catch (error: any) {
      console.error("Error fetching department:", error.message);
      throw error;
    }
  },

  async createDepartment(data: CreateDepartmentRequest): Promise<Department> {
    try {
      const response = await apiClient.post("/departments", data);
      return response.data;
    } catch (error: any) {
      console.error("Error creating department:", error.message);
      throw error;
    }
  },

  async updateDepartment(id: string, data: UpdateDepartmentRequest): Promise<Department> {
    try {
      const response = await apiClient.put(`/departments/${id}`, data);
      return response.data;
    } catch (error: any) {
      console.error("Error updating department:", error.message);
      throw error;
    }
  },

  async deleteDepartment(id: string): Promise<void> {
    try {
      await apiClient.delete(`/departments/${id}`);
    } catch (error: any) {
      console.error("Error deleting department:", error.message);
      throw error;
    }
  },
};
