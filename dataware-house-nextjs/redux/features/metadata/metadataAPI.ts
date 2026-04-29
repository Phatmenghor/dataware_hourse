import apiClient from "@/lib/api-client";
import { Metadata } from "@/types/metadata";

export const metadataAPI = {
  async getAllMetadata(): Promise<Metadata[]> {
    try {
      const response = await apiClient.get("/metadata");
      return response.data || [];
    } catch (error: any) {
      console.error("Error fetching metadata:", error.message);
      throw error;
    }
  },
};
