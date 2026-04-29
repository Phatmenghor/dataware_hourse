import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RolesState, FetchRolesPayload, FetchRolePayload, CreateRolePayload, UpdateRolePayload, DeleteRolePayload, SetErrorPayload } from "./types";

const initialState: RolesState = {
  roles: [],
  selectedRole: null,
  loading: false,
  error: null,
  success: false,
};

const rolesSlice = createSlice({
  name: "roles",
  initialState,
  reducers: {
    fetchRolesStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    fetchRolesSuccess: (state, action: PayloadAction<FetchRolesPayload>) => {
      state.roles = action.payload.data;
      state.loading = false;
      state.success = true;
    },
    fetchRoleSuccess: (state, action: PayloadAction<FetchRolePayload>) => {
      state.selectedRole = action.payload.data;
      state.loading = false;
    },
    fetchRolesError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    createRoleStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    createRoleSuccess: (state, action: PayloadAction<CreateRolePayload>) => {
      state.roles.push(action.payload.data);
      state.loading = false;
      state.success = true;
    },
    createRoleError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    updateRoleStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    updateRoleSuccess: (state, action: PayloadAction<UpdateRolePayload>) => {
      const index = state.roles.findIndex((r) => r.id === action.payload.data.id);
      if (index !== -1) {
        state.roles[index] = action.payload.data;
      }
      state.loading = false;
      state.success = true;
    },
    updateRoleError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    deleteRoleStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    deleteRoleSuccess: (state, action: PayloadAction<DeleteRolePayload>) => {
      state.roles = state.roles.filter((r) => r.id !== action.payload.roleId);
      state.loading = false;
      state.success = true;
    },
    deleteRoleError: (state, action: PayloadAction<SetErrorPayload>) => {
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
  fetchRolesStart,
  fetchRolesSuccess,
  fetchRoleSuccess,
  fetchRolesError,
  createRoleStart,
  createRoleSuccess,
  createRoleError,
  updateRoleStart,
  updateRoleSuccess,
  updateRoleError,
  deleteRoleStart,
  deleteRoleSuccess,
  deleteRoleError,
  clearError,
  resetSuccess,
} = rolesSlice.actions;

export default rolesSlice.reducer;
