import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { MetadataState, FetchMetadataPayload, SetErrorPayload } from "./types";

const initialState: MetadataState = {
  metadata: [],
  selectedMetadata: null,
  loading: false,
  error: null,
  success: false,
};

const metadataSlice = createSlice({
  name: "metadata",
  initialState,
  reducers: {
    fetchMetadataStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    fetchMetadataSuccess: (state, action: PayloadAction<FetchMetadataPayload>) => {
      state.metadata = action.payload.data;
      state.loading = false;
      state.success = true;
    },
    fetchMetadataError: (state, action: PayloadAction<SetErrorPayload>) => {
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
  fetchMetadataStart,
  fetchMetadataSuccess,
  fetchMetadataError,
  clearError,
  resetSuccess,
} = metadataSlice.actions;

export default metadataSlice.reducer;
