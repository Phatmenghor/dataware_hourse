import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { UnitsState, FetchUnitsPayload, SetErrorPayload } from "./types";

const initialState: UnitsState = {
  units: [],
  selectedUnit: null,
  loading: false,
  error: null,
  success: false,
};

const unitsSlice = createSlice({
  name: "units",
  initialState,
  reducers: {
    fetchUnitsStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    fetchUnitsSuccess: (state, action: PayloadAction<FetchUnitsPayload>) => {
      state.units = action.payload.data;
      state.loading = false;
      state.success = true;
    },
    fetchUnitsError: (state, action: PayloadAction<SetErrorPayload>) => {
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
  fetchUnitsStart,
  fetchUnitsSuccess,
  fetchUnitsError,
  clearError,
  resetSuccess,
} = unitsSlice.actions;

export default unitsSlice.reducer;
