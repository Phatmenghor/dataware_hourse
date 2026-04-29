import apiClient from "@/lib/api-client";

export async function getAllPositionsService() {
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
}

export async function getPositionByIdService(id: string) {
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
}

export async function createPositionService(positionData: any) {
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
}

export async function updatePositionService(id: string, positionData: any) {
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
}

export async function deletePositionService(id: string) {
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
}
