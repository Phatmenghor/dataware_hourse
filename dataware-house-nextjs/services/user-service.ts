import apiClient from "@/lib/api-client";

export async function getAllUsersService() {
	try {
		const response = await apiClient.get("/users");
		return {
			success: true,
			data: response.data || [],
		};
	} catch (error: any) {
		console.error("Error fetching users:", error.message);
		return {
			success: false,
			data: [],
			error: error.message,
		};
	}
}

export async function getUserByIdService(id: string) {
	try {
		const response = await apiClient.get(`/users/${id}`);
		return {
			success: true,
			data: response.data,
		};
	} catch (error: any) {
		console.error("Error fetching user:", error.message);
		return {
			success: false,
			data: null,
			error: error.message,
		};
	}
}

export async function createUserService(userData: any) {
	try {
		const response = await apiClient.post("/users", userData);
		return {
			success: true,
			data: response.data,
		};
	} catch (error: any) {
		console.error("Error creating user:", error.message);
		return {
			success: false,
			data: null,
			error: error.message,
		};
	}
}

export async function updateUserService(id: string, userData: any) {
	try {
		const response = await apiClient.put(`/users/${id}`, userData);
		return {
			success: true,
			data: response.data,
		};
	} catch (error: any) {
		console.error("Error updating user:", error.message);
		return {
			success: false,
			data: null,
			error: error.message,
		};
	}
}

export async function deleteUserService(id: string) {
	try {
		await apiClient.delete(`/users/${id}`);
		return {
			success: true,
		};
	} catch (error: any) {
		console.error("Error deleting user:", error.message);
		return {
			success: false,
			error: error.message,
		};
	}
}

export async function getUserProfileService() {
	try {
		const response = await apiClient.post("/users/profile", {});
		return {
			success: true,
			data: response.data,
		};
	} catch (error: any) {
		console.error("Error fetching profile:", error.message);
		return {
			success: false,
			data: null,
			error: error.message,
		};
	}
}
