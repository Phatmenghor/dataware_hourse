import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { PositionsState, FetchPositionsPayload, FetchPositionPayload, CreatePositionPayload, UpdatePositionPayload, DeletePositionPayload, SetErrorPayload } from "./types";

const initialState: PositionsState = {
  positions: [],
  selectedPosition: null,
  loading: false,
  error: null,
  success: false,
};

const positionsSlice = createSlice({
  name: "positions",
  initialState,
  reducers: {
    fetchPositionsStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    fetchPositionsSuccess: (state, action: PayloadAction<FetchPositionsPayload>) => {
      state.positions = action.payload.data;
      state.loading = false;
      state.success = true;
    },
    fetchPositionSuccess: (state, action: PayloadAction<FetchPositionPayload>) => {
      state.selectedPosition = action.payload.data;
      state.loading = false;
    },
    fetchPositionsError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    createPositionStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    createPositionSuccess: (state, action: PayloadAction<CreatePositionPayload>) => {
      state.positions.push(action.payload.data);
      state.loading = false;
      state.success = true;
    },
    createPositionError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    updatePositionStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    updatePositionSuccess: (state, action: PayloadAction<UpdatePositionPayload>) => {
      const index = state.positions.findIndex((p) => p.id === action.payload.data.id);
      if (index !== -1) {
        state.positions[index] = action.payload.data;
      }
      state.loading = false;
      state.success = true;
    },
    updatePositionError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    deletePositionStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    deletePositionSuccess: (state, action: PayloadAction<DeletePositionPayload>) => {
      state.positions = state.positions.filter((p) => p.id !== action.payload.positionId);
      state.loading = false;
      state.success = true;
    },
    deletePositionError: (state, action: PayloadAction<SetErrorPayload>) => {
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
  fetchPositionsStart,
  fetchPositionsSuccess,
  fetchPositionSuccess,
  fetchPositionsError,
  createPositionStart,
  createPositionSuccess,
  createPositionError,
  updatePositionStart,
  updatePositionSuccess,
  updatePositionError,
  deletePositionStart,
  deletePositionSuccess,
  deletePositionError,
  clearError,
  resetSuccess,
} = positionsSlice.actions;

export default positionsSlice.reducer;
