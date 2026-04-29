import apiClient from "@/lib/api-client";
import { Unit } from "@/types/unit";

export const unitsAPI = {
  async getAllUnits(): Promise<Unit[]> {
    try {
      const response = await apiClient.get("/units");
      return response.data || [];
    } catch (error: any) {
      console.error("Error fetching units:", error.message);
      throw error;
    }
  },
};
