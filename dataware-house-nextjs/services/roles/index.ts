import apiClient from "@/lib/api-client";
import { Role, CreateRoleRequest, UpdateRoleRequest } from "@/types/role";
import { ApiResponse } from "@/types";

export const roleService = {
  async getAllRoles(): Promise<ApiResponse<Role[]>> {
    try {
      const response = await apiClient.get("/roles");
      return {
        success: true,
        data: response.data || [],
      };
    } catch (error: any) {
      console.error("Error fetching roles:", error.message);
      return {
        success: false,
        data: [],
        error: error.message,
      };
    }
  },

  async getRoleById(id: string): Promise<ApiResponse<Role>> {
    try {
      const response = await apiClient.get(`/roles/${id}`);
      return {
        success: true,
        data: response.data,
      };
    } catch (error: any) {
      console.error("Error fetching role:", error.message);
      return {
        success: false,
        data: null,
        error: error.message,
      };
    }
  },

  async createRole(roleData: CreateRoleRequest): Promise<ApiResponse<Role>> {
    try {
      const response = await apiClient.post("/roles", roleData);
      return {
        success: true,
        data: response.data,
      };
    } catch (error: any) {
      console.error("Error creating role:", error.message);
      return {
        success: false,
        data: null,
        error: error.message,
      };
    }
  },

  async updateRole(id: string, roleData: UpdateRoleRequest): Promise<ApiResponse<Role>> {
    try {
      const response = await apiClient.put(`/roles/${id}`, roleData);
      return {
        success: true,
        data: response.data,
      };
    } catch (error: any) {
      console.error("Error updating role:", error.message);
      return {
        success: false,
        data: null,
        error: error.message,
      };
    }
  },

  async deleteRole(id: string): Promise<ApiResponse<void>> {
    try {
      await apiClient.delete(`/roles/${id}`);
      return {
        success: true,
      };
    } catch (error: any) {
      console.error("Error deleting role:", error.message);
      return {
        success: false,
        error: error.message,
      };
    }
  },
};

// Backward compatibility exports
export async function getAllRolesService() {
  return roleService.getAllRoles();
}

export async function getRoleByIdService(id: string) {
  return roleService.getRoleById(id);
}

export async function createRoleService(roleData: any) {
  return roleService.createRole(roleData);
}

export async function updateRoleService(id: string, roleData: any) {
  return roleService.updateRole(id, roleData);
}

export async function deleteRoleService(id: string) {
  return roleService.deleteRole(id);
}
