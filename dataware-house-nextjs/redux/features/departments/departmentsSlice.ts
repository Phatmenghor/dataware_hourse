import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { DepartmentsState, FetchDepartmentsPayload, FetchDepartmentPayload, CreateDepartmentPayload, UpdateDepartmentPayload, DeleteDepartmentPayload, SetErrorPayload } from "./types";

const initialState: DepartmentsState = {
  departments: [],
  selectedDepartment: null,
  loading: false,
  error: null,
  success: false,
};

const departmentsSlice = createSlice({
  name: "departments",
  initialState,
  reducers: {
    fetchDepartmentsStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    fetchDepartmentsSuccess: (state, action: PayloadAction<FetchDepartmentsPayload>) => {
      state.departments = action.payload.data;
      state.loading = false;
      state.success = true;
    },
    fetchDepartmentSuccess: (state, action: PayloadAction<FetchDepartmentPayload>) => {
      state.selectedDepartment = action.payload.data;
      state.loading = false;
    },
    fetchDepartmentsError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    createDepartmentStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    createDepartmentSuccess: (state, action: PayloadAction<CreateDepartmentPayload>) => {
      state.departments.push(action.payload.data);
      state.loading = false;
      state.success = true;
    },
    createDepartmentError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    updateDepartmentStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    updateDepartmentSuccess: (state, action: PayloadAction<UpdateDepartmentPayload>) => {
      const index = state.departments.findIndex((d) => d.id === action.payload.data.id);
      if (index !== -1) {
        state.departments[index] = action.payload.data;
      }
      state.loading = false;
      state.success = true;
    },
    updateDepartmentError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    deleteDepartmentStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    deleteDepartmentSuccess: (state, action: PayloadAction<DeleteDepartmentPayload>) => {
      state.departments = state.departments.filter((d) => d.id !== action.payload.departmentId);
      state.loading = false;
      state.success = true;
    },
    deleteDepartmentError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    clearError: (state) => {
      state.error = null;
    },
    resetSuccess: (state) => {
      state.success = false;
    },
  },
});

export const {
  fetchDepartmentsStart,
  fetchDepartmentsSuccess,
  fetchDepartmentSuccess,
  fetchDepartmentsError,
  createDepartmentStart,
  createDepartmentSuccess,
  createDepartmentError,
  updateDepartmentStart,
  updateDepartmentSuccess,
  updateDepartmentError,
  deleteDepartmentStart,
  deleteDepartmentSuccess,
  deleteDepartmentError,
  clearError,
  resetSuccess,
} = departmentsSlice.actions;

export default departmentsSlice.reducer;
