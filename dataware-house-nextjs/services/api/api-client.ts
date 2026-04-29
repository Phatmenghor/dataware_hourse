import apiClient from "@/lib/api-client";

/**
 * Wrapper around the HTTP client for making API calls
 * Provides consistent error handling and response formatting
 */

export const makeRequest = async <T = any>(
  method: 'get' | 'post' | 'put' | 'delete' | 'patch',
  url: string,
  data?: any
): Promise<{ success: boolean; data?: T; error?: string }> => {
  try {
    let response;
    switch (method) {
      case 'get':
        response = await apiClient.get(url);
        break;
      case 'post':
        response = await apiClient.post(url, data);
        break;
      case 'put':
        response = await apiClient.put(url, data);
        break;
      case 'delete':
        response = await apiClient.delete(url);
        break;
      case 'patch':
        response = await apiClient.patch(url, data);
        break;
      default:
        throw new Error(`Unknown method: ${method}`);
    }
    return {
      success: true,
      data: response.data,
    };
  } catch (error: any) {
    console.error(`API Error [${method.toUpperCase()} ${url}]:`, error.message);
    return {
      success: false,
      data: undefined,
      error: error.message || 'An error occurred',
    };
  }
};

export default makeRequest;
