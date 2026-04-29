// Common API response type
export interface ApiResponse<T = any> {
  success: boolean;
  data?: T | null;
  error?: string;
  message?: string;
}

// Pagination
export interface PaginationParams {
  page?: number;
  limit?: number;
  skip?: number;
  take?: number;
}

export interface PaginatedResponse<T> {
  data: T[];
  total: number;
  page: number;
  limit: number;
  totalPages: number;
}

// Common status enum
export enum Status {
  ACTIVE = 'ACTIVE',
  INACTIVE = 'INACTIVE',
  PENDING = 'PENDING',
}

// Common error type
export interface ApiError {
  message: string;
  code?: string;
  statusCode?: number;
}

// Async state type
export interface AsyncState<T> {
  loading: boolean;
  error: string | null;
  data: T | null;
}

// Export all feature types
export * from "./auth";
export * from "./user";
export * from "./department";
export * from "./position";
export * from "./role";
export * from "./unit";
export * from "./widget";
export * from "./report";
export * from "./metadata";
