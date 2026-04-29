import apiClient from "@/lib/api-client";

export async function getAllUnitsService() {
	try {
		const response = await apiClient.get("/units");
		return {
			success: true,
			data: response.data || [],
		};
	} catch (error: any) {
		console.error("Error fetching units:", error.message);
		return {
			success: false,
			data: [],
			error: error.message,
		};
	}
}

export async function getUnitsByDepartmentService(departmentId: string) {
	try {
		const response = await apiClient.get(`/units/department/${departmentId}`);
		return {
			success: true,
			data: response.data || [],
		};
	} catch (error: any) {
		console.error("Error fetching units by department:", error.message);
		return {
			success: false,
			data: [],
			error: error.message,
		};
	}
}

export async function getUnitByIdService(id: string) {
	try {
		const response = await apiClient.get(`/units/${id}`);
		return {
			success: true,
			data: response.data,
		};
	} catch (error: any) {
		console.error("Error fetching unit:", error.message);
		return {
			success: false,
			data: null,
			error: error.message,
		};
	}
}

export async function createUnitService(unitData: any) {
	try {
		const response = await apiClient.post("/units", unitData);
		return {
			success: true,
			data: response.data,
		};
	} catch (error: any) {
		console.error("Error creating unit:", error.message);
		return {
			success: false,
			data: null,
			error: error.message,
		};
	}
}

export async function updateUnitService(id: string, unitData: any) {
	try {
		const response = await apiClient.put(`/units/${id}`, unitData);
		return {
			success: true,
			data: response.data,
		};
	} catch (error: any) {
		console.error("Error updating unit:", error.message);
		return {
			success: false,
			data: null,
			error: error.message,
		};
	}
}

export async function deleteUnitService(id: string) {
	try {
		await apiClient.delete(`/units/${id}`);
		return {
			success: true,
		};
	} catch (error: any) {
		console.error("Error deleting unit:", error.message);
		return {
			success: false,
			error: error.message,
		};
	}
}
