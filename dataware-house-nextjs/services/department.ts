import apiClient from "@/lib/api-client";
import { Department, CreateDepartmentRequest, UpdateDepartmentRequest } from "@/types/department";
import { ApiResponse } from "@/types";

export const departmentService = {
  async getAllDepartments(): Promise<ApiResponse<Department[]>> {
    try {
      const response = await apiClient.get("/departments");
      return {
        success: true,
        data: response.data || [],
      };
    } catch (error: any) {
      console.error("Error fetching departments:", error.message);
      return {
        success: false,
        data: [],
        error: error.message,
      };
    }
  },

  async getDepartmentById(id: string): Promise<ApiResponse<Department>> {
    try {
      const response = await apiClient.get(`/departments/${id}`);
      return {
        success: true,
        data: response.data,
      };
    } catch (error: any) {
      console.error("Error fetching department:", error.message);
      return {
        success: false,
        data: null,
        error: error.message,
      };
    }
  },

  async createDepartment(departmentData: CreateDepartmentRequest): Promise<ApiResponse<Department>> {
    try {
      const response = await apiClient.post("/departments", departmentData);
      return {
        success: true,
        data: response.data,
      };
    } catch (error: any) {
      console.error("Error creating department:", error.message);
      return {
        success: false,
        data: null,
        error: error.message,
      };
    }
  },

  async updateDepartment(
    id: string,
    departmentData: UpdateDepartmentRequest
  ): Promise<ApiResponse<Department>> {
    try {
      const response = await apiClient.put(`/departments/${id}`, departmentData);
      return {
        success: true,
        data: response.data,
      };
    } catch (error: any) {
      console.error("Error updating department:", error.message);
      return {
        success: false,
        data: null,
        error: error.message,
      };
    }
  },

  async deleteDepartment(id: string): Promise<ApiResponse<void>> {
    try {
      await apiClient.delete(`/departments/${id}`);
      return {
        success: true,
      };
    } catch (error: any) {
      console.error("Error deleting department:", error.message);
      return {
        success: false,
        error: error.message,
      };
    }
  },
};

// Backward compatibility exports
export async function getAllDepartmentsService() {
  return departmentService.getAllDepartments();
}

export async function getDepartmentByIdService(id: string) {
  return departmentService.getDepartmentById(id);
}

export async function createDepartmentService(departmentData: any) {
  return departmentService.createDepartment(departmentData);
}

export async function updateDepartmentService(id: string, departmentData: any) {
  return departmentService.updateDepartment(id, departmentData);
}

export async function deleteDepartmentService(id: string) {
  return departmentService.deleteDepartment(id);
}
