import apiClient from "@/lib/api-client";
import { Role, CreateRoleRequest, UpdateRoleRequest } from "@/types/role";

export const rolesAPI = {
  async getAllRoles(): Promise<Role[]> {
    try {
      const response = await apiClient.get("/roles");
      return response.data || [];
    } catch (error: any) {
      console.error("Error fetching roles:", error.message);
      throw error;
    }
  },

  async getRoleById(id: string): Promise<Role> {
    try {
      const response = await apiClient.get(`/roles/${id}`);
      return response.data;
    } catch (error: any) {
      console.error("Error fetching role:", error.message);
      throw error;
    }
  },

  async createRole(data: CreateRoleRequest): Promise<Role> {
    try {
      const response = await apiClient.post("/roles", data);
      return response.data;
    } catch (error: any) {
      console.error("Error creating role:", error.message);
      throw error;
    }
  },

  async updateRole(id: string, data: UpdateRoleRequest): Promise<Role> {
    try {
      const response = await apiClient.put(`/roles/${id}`, data);
      return response.data;
    } catch (error: any) {
      console.error("Error updating role:", error.message);
      throw error;
    }
  },

  async deleteRole(id: string): Promise<void> {
    try {
      await apiClient.delete(`/roles/${id}`);
    } catch (error: any) {
      console.error("Error deleting role:", error.message);
      throw error;
    }
  },
};
