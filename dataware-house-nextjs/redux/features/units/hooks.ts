import { useDispatch, useSelector } from "react-redux";
import { RootState, AppDispatch } from "@/redux/store";
import {
  fetchUnitsStart,
  fetchUnitsSuccess,
  fetchUnitsError,
  clearError,
  resetSuccess,
} from "./unitsSlice";
import { unitsAPI } from "./unitsAPI";

export const useUnits = () => {
  const dispatch = useDispatch<AppDispatch>();
  const { units, loading, error, success } = useSelector((state: RootState) => state.units);

  const fetchAllUnits = async () => {
    dispatch(fetchUnitsStart());
    try {
      const data = await unitsAPI.getAllUnits();
      dispatch(fetchUnitsSuccess({ data }));
    } catch (err: any) {
      dispatch(fetchUnitsError({ error: err.message }));
    }
  };

  const handleClearError = () => {
    dispatch(clearError());
  };

  const handleResetSuccess = () => {
    dispatch(resetSuccess());
  };

  return {
    units,
    loading,
    error,
    success,
    fetchAllUnits,
    clearError: handleClearError,
    resetSuccess: handleResetSuccess,
  };
};
