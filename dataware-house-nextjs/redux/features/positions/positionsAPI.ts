import apiClient from "@/lib/api-client";
import { Position, CreatePositionRequest, UpdatePositionRequest } from "@/types/position";

export const positionsAPI = {
  async getAllPositions(): Promise<Position[]> {
    try {
      const response = await apiClient.get("/positions");
      return response.data || [];
    } catch (error: any) {
      console.error("Error fetching positions:", error.message);
      throw error;
    }
  },

  async getPositionById(id: string): Promise<Position> {
    try {
      const response = await apiClient.get(`/positions/${id}`);
      return response.data;
    } catch (error: any) {
      console.error("Error fetching position:", error.message);
      throw error;
    }
  },

  async createPosition(data: CreatePositionRequest): Promise<Position> {
    try {
      const response = await apiClient.post("/positions", data);
      return response.data;
    } catch (error: any) {
      console.error("Error creating position:", error.message);
      throw error;
    }
  },

  async updatePosition(id: string, data: UpdatePositionRequest): Promise<Position> {
    try {
      const response = await apiClient.put(`/positions/${id}`, data);
      return response.data;
    } catch (error: any) {
      console.error("Error updating position:", error.message);
      throw error;
    }
  },

  async deletePosition(id: string): Promise<void> {
    try {
      await apiClient.delete(`/positions/${id}`);
    } catch (error: any) {
      console.error("Error deleting position:", error.message);
      throw error;
    }
  },
};
