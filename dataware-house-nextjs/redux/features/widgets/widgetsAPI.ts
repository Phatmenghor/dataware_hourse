import apiClient from "@/lib/api-client";
import { Widget } from "@/types/widget";

export const widgetsAPI = {
  async getAllWidgets(): Promise<Widget[]> {
    try {
      const response = await apiClient.get("/widgets");
      return response.data || [];
    } catch (error: any) {
      console.error("Error fetching widgets:", error.message);
      throw error;
    }
  },
};
