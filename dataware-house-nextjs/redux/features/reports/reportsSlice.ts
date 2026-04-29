import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { ReportsState, FetchReportsPayload, FetchReportPayload, CreateReportPayload, UpdateReportPayload, DeleteReportPayload, SetErrorPayload } from "./types";

const initialState: ReportsState = {
  reports: [],
  selectedReport: null,
  loading: false,
  error: null,
  success: false,
};

const reportsSlice = createSlice({
  name: "reports",
  initialState,
  reducers: {
    fetchReportsStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    fetchReportsSuccess: (state, action: PayloadAction<FetchReportsPayload>) => {
      state.reports = action.payload.data;
      state.loading = false;
      state.success = true;
    },
    fetchReportSuccess: (state, action: PayloadAction<FetchReportPayload>) => {
      state.selectedReport = action.payload.data;
      state.loading = false;
    },
    fetchReportsError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    createReportStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    createReportSuccess: (state, action: PayloadAction<CreateReportPayload>) => {
      state.reports.push(action.payload.data);
      state.loading = false;
      state.success = true;
    },
    createReportError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    updateReportStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    updateReportSuccess: (state, action: PayloadAction<UpdateReportPayload>) => {
      const index = state.reports.findIndex((r) => r.id === action.payload.data.id);
      if (index !== -1) {
        state.reports[index] = action.payload.data;
      }
      state.loading = false;
      state.success = true;
    },
    updateReportError: (state, action: PayloadAction<SetErrorPayload>) => {
      state.error = action.payload.error;
      state.loading = false;
    },
    deleteReportStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    deleteReportSuccess: (state, action: PayloadAction<DeleteReportPayload>) => {
      state.reports = state.reports.filter((r) => r.id !== action.payload.reportId);
      state.loading = false;
      state.success = true;
    },
    deleteReportError: (state, action: PayloadAction<SetErrorPayload>) => {
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
  fetchReportsStart,
  fetchReportsSuccess,
  fetchReportSuccess,
  fetchReportsError,
  createReportStart,
  createReportSuccess,
  createReportError,
  updateReportStart,
  updateReportSuccess,
  updateReportError,
  deleteReportStart,
  deleteReportSuccess,
  deleteReportError,
  clearError,
  resetSuccess,
} = reportsSlice.actions;

export default reportsSlice.reducer;
