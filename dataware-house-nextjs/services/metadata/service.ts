import apiClient from "@/lib/api-client";

export async function getAllMetadataService() {
	try {
		const response = await apiClient.get("/metadata");
		return {
			success: true,
			data: response.data || [],
		};
	} catch (error: any) {
		console.error("Error fetching metadata:", error.message);
		return {
			success: false,
			data: [],
			error: error.message,
		};
	}
}

export async function getMetadataByTypeService(type: string) {
	try {
		const response = await apiClient.get(`/metadata/type/${type}`);
		return {
			success: true,
			data: response.data || [],
		};
	} catch (error: any) {
		console.error("Error fetching metadata by type:", error.message);
		return {
			success: false,
			data: [],
			error: error.message,
		};
	}
}

export async function getMetadataByIdService(id: string) {
	try {
		const response = await apiClient.get(`/metadata/${id}`);
		return {
			success: true,
			data: response.data,
		};
	} catch (error: any) {
		console.error("Error fetching metadata:", error.message);
		return {
			success: false,
			data: null,
			error: error.message,
		};
	}
}

export async function deleteMetadataService(id: string) {
	try {
		await apiClient.delete(`/metadata/${id}`);
		return {
			success: true,
		};
	} catch (error: any) {
		console.error("Error deleting metadata:", error.message);
		return {
			success: false,
			error: error.message,
		};
	}
}
