import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { UsersState, FetchUsersPayload, FetchUserPayload, CreateUserPayload, UpdateUserPayload, DeleteUserPayload, SetErrorPayload } from "./types";
import { User, UserProfile } from "@/types/user";

const initialState: UsersState = {
  users: [],
  currentUser: null,
  selectedUser: null,
  loading: false,
  error: null,
  success: false,
};

const usersSlice = createSlice({
  name: "users",
  initialState,
  reducers: {
    fetchUsersStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    fetchUsersSuccess: (state, action: PayloadAction<FetchUsersPayload>) => {
      state.users = action.payload.data;
      state.loading = false;
      state.success = true;
    },
    fetchUserSuccess: (state, action: PayloadAction<FetchUserPayload>) => {
      state.selectedUser = action.payload.data;
      state.loading = false;
    },
    fetchUsersError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    createUserStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    createUserSuccess: (state, action: PayloadAction<CreateUserPayload>) => {
      state.users.push(action.payload.data);
      state.loading = false;
      state.success = true;
    },
    createUserError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    updateUserStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    updateUserSuccess: (state, action: PayloadAction<UpdateUserPayload>) => {
      const index = state.users.findIndex((u) => u.id === action.payload.data.id);
      if (index !== -1) {
        state.users[index] = action.payload.data;
      }
      state.loading = false;
      state.success = true;
    },
    updateUserError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    deleteUserStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    deleteUserSuccess: (state, action: PayloadAction<DeleteUserPayload>) => {
      state.users = state.users.filter((u) => u.id !== action.payload.userId);
      state.loading = false;
      state.success = true;
    },
    deleteUserError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    setCurrentUser: (state, action: PayloadAction<{ data: UserProfile }>) => {
      state.currentUser = action.payload.data;
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
  fetchUsersStart,
  fetchUsersSuccess,
  fetchUserSuccess,
  fetchUsersError,
  createUserStart,
  createUserSuccess,
  createUserError,
  updateUserStart,
  updateUserSuccess,
  updateUserError,
  deleteUserStart,
  deleteUserSuccess,
  deleteUserError,
  setCurrentUser,
  clearError,
  resetSuccess,
} = usersSlice.actions;

export default usersSlice.reducer;
