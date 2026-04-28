import axios, { AxiosInstance, AxiosRequestConfig } from "axios";
import { getToken } from "./init-token";
import { TOKEN_COOKIES } from "@/constant/api-end-point";

const API_BASE_URL = process.env.NEXT_PUBLIC_BACKEND_URL || "http://localhost:8081/api/v1";

class ApiClient {
	private axiosInstance: AxiosInstance;

	constructor() {
		this.axiosInstance = axios.create({
			baseURL: API_BASE_URL,
			timeout: 30000,
			headers: {
				"Content-Type": "application/json",
				Accept: "application/json",
			},
		});

		this.setupInterceptors();
	}

	private setupInterceptors() {
		this.axiosInstance.interceptors.request.use(
			(config) => {
				const token = getToken(TOKEN_COOKIES.TOKEN_NAME);
				if (token) {
					config.headers.Authorization = `Bearer ${token}`;
				}
				return config;
			},
			(error) => Promise.reject(error)
		);

		this.axiosInstance.interceptors.response.use(
			(response) => response.data,
			(error) => {
				console.error("API Error:", error.response?.data || error.message);
				return Promise.reject(error);
			}
		);
	}

	get<T = any>(url: string, config?: AxiosRequestConfig) {
		return this.axiosInstance.get<any, T>(url, config);
	}

	post<T = any>(url: string, data?: any, config?: AxiosRequestConfig) {
		return this.axiosInstance.post<any, T>(url, data, config);
	}

	put<T = any>(url: string, data?: any, config?: AxiosRequestConfig) {
		return this.axiosInstance.put<any, T>(url, data, config);
	}

	delete<T = any>(url: string, config?: AxiosRequestConfig) {
		return this.axiosInstance.delete<any, T>(url, config);
	}

	patch<T = any>(url: string, data?: any, config?: AxiosRequestConfig) {
		return this.axiosInstance.patch<any, T>(url, data, config);
	}
}

export default new ApiClient();
