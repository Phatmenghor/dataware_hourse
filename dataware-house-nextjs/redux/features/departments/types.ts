import { Department } from "@/types/department";

export interface DepartmentsState {
  departments: Department[];
  selectedDepartment: Department | null;
  loading: boolean;
  error: string | null;
  success: boolean;
}

export interface FetchDepartmentsPayload {
  data: Department[];
}

export interface FetchDepartmentPayload {
  data: Department;
}

export interface CreateDepartmentPayload {
  data: Department;
}

export interface UpdateDepartmentPayload {
  data: Department;
}

export interface DeleteDepartmentPayload {
  departmentId: string;
}

export interface SetErrorPayload {
  error: string;
}
