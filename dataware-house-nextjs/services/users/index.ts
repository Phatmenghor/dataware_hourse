import apiClient from "@/lib/api-client";
import { User, CreateUserRequest, UpdateUserRequest, UserProfile } from "@/types/user";
import { ApiResponse } from "@/types";

export const userService = {
  async getAllUsers(): Promise<ApiResponse<User[]>> {
    try {
      const response = await apiClient.get("/users");
      return {
        success: true,
        data: response.data || [],
      };
    } catch (error: any) {
      console.error("Error fetching users:", error.message);
      return {
        success: false,
        data: [],
        error: error.message,
      };
    }
  },

  async getUserById(id: string): Promise<ApiResponse<User>> {
    try {
      const response = await apiClient.get(`/users/${id}`);
      return {
        success: true,
        data: response.data,
      };
    } catch (error: any) {
      console.error("Error fetching user:", error.message);
      return {
        success: false,
        data: null,
        error: error.message,
      };
    }
  },

  async createUser(userData: CreateUserRequest): Promise<ApiResponse<User>> {
    try {
      const response = await apiClient.post("/users", userData);
      return {
        success: true,
        data: response.data,
      };
    } catch (error: any) {
      console.error("Error creating user:", error.message);
      return {
        success: false,
        data: null,
        error: error.message,
      };
    }
  },

  async updateUser(id: string, userData: UpdateUserRequest): Promise<ApiResponse<User>> {
    try {
      const response = await apiClient.put(`/users/${id}`, userData);
      return {
        success: true,
        data: response.data,
      };
    } catch (error: any) {
      console.error("Error updating user:", error.message);
      return {
        success: false,
        data: null,
        error: error.message,
      };
    }
  },

  async deleteUser(id: string): Promise<ApiResponse<void>> {
    try {
      await apiClient.delete(`/users/${id}`);
      return {
        success: true,
      };
    } catch (error: any) {
      console.error("Error deleting user:", error.message);
      return {
        success: false,
        error: error.message,
      };
    }
  },

  async getUserProfile(): Promise<ApiResponse<UserProfile>> {
    try {
      const response = await apiClient.post("/users/profile", {});
      return {
        success: true,
        data: response.data,
      };
    } catch (error: any) {
      console.error("Error fetching profile:", error.message);
      return {
        success: false,
        data: null,
        error: error.message,
      };
    }
  },
};

// Backward compatibility exports
export async function getAllUsersService() {
  return userService.getAllUsers();
}

export async function getUserByIdService(id: string) {
  return userService.getUserById(id);
}

export async function createUserService(userData: any) {
  return userService.createUser(userData);
}

export async function updateUserService(id: string, userData: any) {
  return userService.updateUser(id, userData);
}

export async function deleteUserService(id: string) {
  return userService.deleteUser(id);
}

export async function getUserProfileService() {
  return userService.getUserProfile();
}
