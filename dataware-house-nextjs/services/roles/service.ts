import apiClient from "@/lib/api-client";

export async function getAllRolesService() {
	try {
		const response = await apiClient.get("/roles");
		return {
			success: true,
			data: response.data || [],
		};
	} catch (error: any) {
		console.error("Error fetching roles:", error.message);
		return {
			success: false,
			data: [],
			error: error.message,
		};
	}
}

export async function getRoleByIdService(id: string) {
	try {
		const response = await apiClient.get(`/roles/${id}`);
		return {
			success: true,
			data: response.data,
		};
	} catch (error: any) {
		console.error("Error fetching role:", error.message);
		return {
			success: false,
			data: null,
			error: error.message,
		};
	}
}

export async function createRoleService(roleData: any) {
	try {
		const response = await apiClient.post("/roles", roleData);
		return {
			success: true,
			data: response.data,
		};
	} catch (error: any) {
		console.error("Error creating role:", error.message);
		return {
			success: false,
			data: null,
			error: error.message,
		};
	}
}

export async function updateRoleService(id: string, roleData: any) {
	try {
		const response = await apiClient.put(`/roles/${id}`, roleData);
		return {
			success: true,
			data: response.data,
		};
	} catch (error: any) {
		console.error("Error updating role:", error.message);
		return {
			success: false,
			data: null,
			error: error.message,
		};
	}
}

export async function deleteRoleService(id: string) {
	try {
		await apiClient.delete(`/roles/${id}`);
		return {
			success: true,
		};
	} catch (error: any) {
		console.error("Error deleting role:", error.message);
		return {
			success: false,
			error: error.message,
		};
	}
}
