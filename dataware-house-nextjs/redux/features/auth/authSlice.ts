import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { AuthState, SetAuthPayload, SetErrorPayload } from "./types";

const initialState: AuthState = {
  isAuthenticated: false,
  user: null,
  token: null,
  loading: false,
  error: null,
  success: false,
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    loginStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    loginSuccess: (state, action: PayloadAction<SetAuthPayload>) => {
      state.isAuthenticated = true;
      state.user = action.payload.user;
      state.token = action.payload.token;
      state.loading = false;
      state.success = true;
    },
    loginError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
      state.isAuthenticated = false;
    },
    logoutStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    logoutSuccess: (state) => {
      state.isAuthenticated = false;
      state.user = null;
      state.token = null;
      state.loading = false;
      state.success = true;
    },
    logoutError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    registerStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    registerSuccess: (state, action: PayloadAction<SetAuthPayload>) => {
      state.isAuthenticated = true;
      state.user = action.payload.user;
      state.token = action.payload.token;
      state.loading = false;
      state.success = true;
    },
    registerError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    refreshTokenStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    refreshTokenSuccess: (state, action: PayloadAction<{ token: string }>) => {
      state.token = action.payload.token;
      state.loading = false;
    },
    refreshTokenError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    setUser: (state, action: PayloadAction<{ user: any }>) => {
      state.user = action.payload.user;
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
  loginStart,
  loginSuccess,
  loginError,
  logoutStart,
  logoutSuccess,
  logoutError,
  registerStart,
  registerSuccess,
  registerError,
  refreshTokenStart,
  refreshTokenSuccess,
  refreshTokenError,
  setUser,
  clearError,
  resetSuccess,
} = authSlice.actions;

export default authSlice.reducer;
