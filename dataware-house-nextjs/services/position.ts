import apiClient from "@/lib/api-client";
import { Position, CreatePositionRequest, UpdatePositionRequest } from "@/types/position";
import { ApiResponse } from "@/types";

export const positionService = {
  async getAllPositions(): Promise<ApiResponse<Position[]>> {
    try {
      const response = await apiClient.get("/positions");
      return {
        success: true,
        data: response.data || [],
      };
    } catch (error: any) {
      console.error("Error fetching positions:", error.message);
      return {
        success: false,
        data: [],
        error: error.message,
      };
    }
  },

  async getPositionById(id: string): Promise<ApiResponse<Position>> {
    try {
      const response = await apiClient.get(`/positions/${id}`);
      return {
        success: true,
        data: response.data,
      };
    } catch (error: any) {
      console.error("Error fetching position:", error.message);
      return {
        success: false,
        data: null,
        error: error.message,
      };
    }
  },

  async createPosition(positionData: CreatePositionRequest): Promise<ApiResponse<Position>> {
    try {
      const response = await apiClient.post("/positions", positionData);
      return {
        success: true,
        data: response.data,
      };
    } catch (error: any) {
      console.error("Error creating position:", error.message);
      return {
        success: false,
        data: null,
        error: error.message,
      };
    }
  },

  async updatePosition(
    id: string,
    positionData: UpdatePositionRequest
  ): Promise<ApiResponse<Position>> {
    try {
      const response = await apiClient.put(`/positions/${id}`, positionData);
      return {
        success: true,
        data: response.data,
      };
    } catch (error: any) {
      console.error("Error updating position:", error.message);
      return {
        success: false,
        data: null,
        error: error.message,
      };
    }
  },

  async deletePosition(id: string): Promise<ApiResponse<void>> {
    try {
      await apiClient.delete(`/positions/${id}`);
      return {
        success: true,
      };
    } catch (error: any) {
      console.error("Error deleting position:", error.message);
      return {
        success: false,
        error: error.message,
      };
    }
  },
};

// Backward compatibility exports
export async function getAllPositionsService() {
  return positionService.getAllPositions();
}

export async function getPositionByIdService(id: string) {
  return positionService.getPositionById(id);
}

export async function createPositionService(positionData: any) {
  return positionService.createPosition(positionData);
}

export async function updatePositionService(id: string, positionData: any) {
  return positionService.updatePosition(id, positionData);
}

export async function deletePositionService(id: string) {
  return positionService.deletePosition(id);
}
