import { useDispatch, useSelector } from "react-redux";
import { RootState, AppDispatch } from "@/redux/store";
import {
  fetchWidgetsStart,
  fetchWidgetsSuccess,
  fetchWidgetsError,
  clearError,
  resetSuccess,
} from "./widgetsSlice";
import { widgetsAPI } from "./widgetsAPI";

export const useWidgets = () => {
  const dispatch = useDispatch<AppDispatch>();
  const { widgets, loading, error, success } = useSelector((state: RootState) => state.widgets);

  const fetchAllWidgets = async () => {
    dispatch(fetchWidgetsStart());
    try {
      const data = await widgetsAPI.getAllWidgets();
      dispatch(fetchWidgetsSuccess({ data }));
    } catch (err: any) {
      dispatch(fetchWidgetsError({ error: err.message }));
    }
  };

  const handleClearError = () => {
    dispatch(clearError());
  };

  const handleResetSuccess = () => {
    dispatch(resetSuccess());
  };

  return {
    widgets,
    loading,
    error,
    success,
    fetchAllWidgets,
    clearError: handleClearError,
    resetSuccess: handleResetSuccess,
  };
};
