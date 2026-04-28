import apiClient from "@/lib/api-client";

export async function getAllWidgetsService() {
	try {
		const response = await apiClient.get("/widgets");
		return {
			success: true,
			data: response.data || [],
		};
	} catch (error: any) {
		console.error("Error fetching widgets:", error.message);
		return {
			success: false,
			data: [],
			error: error.message,
		};
	}
}

export async function getWidgetByIdService(id: string) {
	try {
		const response = await apiClient.get(`/widgets/${id}`);
		return {
			success: true,
			data: response.data,
		};
	} catch (error: any) {
		console.error("Error fetching widget:", error.message);
		return {
			success: false,
			data: null,
			error: error.message,
		};
	}
}

export async function deleteWidgetService(id: string) {
	try {
		await apiClient.delete(`/widgets/${id}`);
		return {
			success: true,
		};
	} catch (error: any) {
		console.error("Error deleting widget:", error.message);
		return {
			success: false,
			error: error.message,
		};
	}
}
