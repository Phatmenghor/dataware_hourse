import { useState, useCallback } from "react";
import apiClient from "@/lib/api-client";

interface UseApiOptions {
  onSuccess?: (data: any) => void;
  onError?: (error: any) => void;
}

interface ApiState {
  data: any;
  loading: boolean;
  error: string | null;
}

export const useApi = (options?: UseApiOptions) => {
  const [state, setState] = useState<ApiState>({
    data: null,
    loading: false,
    error: null,
  });

  const request = useCallback(
    async (method: "get" | "post" | "put" | "patch" | "delete", url: string, payload?: any) => {
      setState({ data: null, loading: true, error: null });
      try {
        const response = await apiClient[method](url, payload);
        setState({ data: response, loading: false, error: null });
        options?.onSuccess?.(response);
        return response;
      } catch (error: any) {
        const errorMessage = error?.response?.data?.message || error.message;
        setState({ data: null, loading: false, error: errorMessage });
        options?.onError?.(error);
        throw error;
      }
    },
    [options]
  );

  return {
    ...state,
    request,
  };
};
