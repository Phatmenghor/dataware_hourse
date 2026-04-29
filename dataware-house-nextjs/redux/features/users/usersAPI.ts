import apiClient from "@/lib/api-client";
import { User, CreateUserRequest, UpdateUserRequest, UserProfile } from "@/types/user";

export const usersAPI = {
  async getAllUsers(): Promise<User[]> {
    try {
      const response = await apiClient.get("/users");
      return response.data || [];
    } catch (error: any) {
      console.error("Error fetching users:", error.message);
      throw error;
    }
  },

  async getUserById(id: string): Promise<User> {
    try {
      const response = await apiClient.get(`/users/${id}`);
      return response.data;
    } catch (error: any) {
      console.error("Error fetching user:", error.message);
      throw error;
    }
  },

  async createUser(userData: CreateUserRequest): Promise<User> {
    try {
      const response = await apiClient.post("/users", userData);
      return response.data;
    } catch (error: any) {
      console.error("Error creating user:", error.message);
      throw error;
    }
  },

  async updateUser(id: string, userData: UpdateUserRequest): Promise<User> {
    try {
      const response = await apiClient.put(`/users/${id}`, userData);
      return response.data;
    } catch (error: any) {
      console.error("Error updating user:", error.message);
      throw error;
    }
  },

  async deleteUser(id: string): Promise<void> {
    try {
      await apiClient.delete(`/users/${id}`);
    } catch (error: any) {
      console.error("Error deleting user:", error.message);
      throw error;
    }
  },

  async getUserProfile(): Promise<UserProfile> {
    try {
      const response = await apiClient.post("/users/profile", {});
      return response.data;
    } catch (error: any) {
      console.error("Error fetching profile:", error.message);
      throw error;
    }
  },
};
