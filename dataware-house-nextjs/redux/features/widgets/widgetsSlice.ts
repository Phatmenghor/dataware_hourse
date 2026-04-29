import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { WidgetsState, FetchWidgetsPayload, SetErrorPayload } from "./types";

const initialState: WidgetsState = {
  widgets: [],
  selectedWidget: null,
  loading: false,
  error: null,
  success: false,
};

const widgetsSlice = createSlice({
  name: "widgets",
  initialState,
  reducers: {
    fetchWidgetsStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    fetchWidgetsSuccess: (state, action: PayloadAction<FetchWidgetsPayload>) => {
      state.widgets = action.payload.data;
      state.loading = false;
      state.success = true;
    },
    fetchWidgetsError: (state, action: PayloadAction<SetErrorPayload>) => {
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
  fetchWidgetsStart,
  fetchWidgetsSuccess,
  fetchWidgetsError,
  clearError,
  resetSuccess,
} = widgetsSlice.actions;

export default widgetsSlice.reducer;
