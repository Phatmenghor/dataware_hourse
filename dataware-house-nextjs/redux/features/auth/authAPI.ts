import apiClient from "@/lib/api-client";
import { LoginRequest, LoginResponse, RegisterRequest, RefreshTokenRequest } from "./types";

export const authAPI = {
  async login(credentials: LoginRequest): Promise<LoginResponse> {
    try {
      const response = await apiClient.post("/auth/login", credentials);
      return response.data;
    } catch (error: any) {
      console.error("Error logging in:", error.message);
      throw error;
    }
  },

  async register(data: RegisterRequest): Promise<LoginResponse> {
    try {
      const response = await apiClient.post("/auth/register", data);
      return response.data;
    } catch (error: any) {
      console.error("Error registering:", error.message);
      throw error;
    }
  },

  async logout(): Promise<void> {
    try {
      await apiClient.post("/auth/logout", {});
    } catch (error: any) {
      console.error("Error logging out:", error.message);
      throw error;
    }
  },

  async refreshToken(request: RefreshTokenRequest): Promise<{ token: string }> {
    try {
      const response = await apiClient.post("/auth/refresh", request);
      return response.data;
    } catch (error: any) {
      console.error("Error refreshing token:", error.message);
      throw error;
    }
  },
};
