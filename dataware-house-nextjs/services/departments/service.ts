import apiClient from "@/lib/api-client";

export async function getAllDepartmentsService() {
	try {
		const response = await apiClient.get("/departments");
		return {
			success: true,
			data: response.data || [],
		};
	} catch (error: any) {
		console.error("Error fetching departments:", error.message);
		return {
			success: false,
			data: [],
			error: error.message,
		};
	}
}

export async function getDepartmentByIdService(id: string) {
	try {
		const response = await apiClient.get(`/departments/${id}`);
		return {
			success: true,
			data: response.data,
		};
	} catch (error: any) {
		console.error("Error fetching department:", error.message);
		return {
			success: false,
			data: null,
			error: error.message,
		};
	}
}

export async function createDepartmentService(departmentData: any) {
	try {
		const response = await apiClient.post("/departments", departmentData);
		return {
			success: true,
			data: response.data,
		};
	} catch (error: any) {
		console.error("Error creating department:", error.message);
		return {
			success: false,
			data: null,
			error: error.message,
		};
	}
}

export async function updateDepartmentService(id: string, departmentData: any) {
	try {
		const response = await apiClient.put(`/departments/${id}`, departmentData);
		return {
			success: true,
			data: response.data,
		};
	} catch (error: any) {
		console.error("Error updating department:", error.message);
		return {
			success: false,
			data: null,
			error: error.message,
		};
	}
}

export async function deleteDepartmentService(id: string) {
	try {
		await apiClient.delete(`/departments/${id}`);
		return {
			success: true,
		};
	} catch (error: any) {
		console.error("Error deleting department:", error.message);
		return {
			success: false,
			error: error.message,
		};
	}
}
