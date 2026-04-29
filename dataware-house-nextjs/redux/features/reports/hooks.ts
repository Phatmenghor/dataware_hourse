import { useDispatch, useSelector } from "react-redux";
import { RootState, AppDispatch } from "@/redux/store";
import {
  fetchReportsStart,
  fetchReportsSuccess,
  fetchReportSuccess,
  fetchReportsError,
  deleteReportStart,
  deleteReportSuccess,
  deleteReportError,
  clearError,
  resetSuccess,
} from "./reportsSlice";
import { reportsAPI } from "./reportsAPI";

export const useReports = () => {
  const dispatch = useDispatch<AppDispatch>();
  const { reports, selectedReport, loading, error, success } = useSelector((state: RootState) => state.reports);

  const fetchAllReports = async () => {
    dispatch(fetchReportsStart());
    try {
      const data = await reportsAPI.getAllReports();
      dispatch(fetchReportsSuccess({ data }));
    } catch (err: any) {
      dispatch(fetchReportsError({ error: err.message }));
    }
  };

  const fetchReportById = async (id: string) => {
    dispatch(fetchReportsStart());
    try {
      const data = await reportsAPI.getReportById(id);
      dispatch(fetchReportSuccess({ data }));
    } catch (err: any) {
      dispatch(fetchReportsError({ error: err.message }));
    }
  };

  const fetchReportByDepartment = async (departmentId: string) => {
    dispatch(fetchReportsStart());
    try {
      const data = await reportsAPI.getReportByDepartment(departmentId);
      dispatch(fetchReportsSuccess({ data }));
    } catch (err: any) {
      dispatch(fetchReportsError({ error: err.message }));
    }
  };

  const deleteReport = async (id: string) => {
    dispatch(deleteReportStart());
    try {
      await reportsAPI.deleteReport(id);
      dispatch(deleteReportSuccess({ reportId: id }));
    } catch (err: any) {
      dispatch(deleteReportError({ error: err.message }));
    }
  };

  const handleClearError = () => {
    dispatch(clearError());
  };

  const handleResetSuccess = () => {
    dispatch(resetSuccess());
  };

  return {
    reports,
    selectedReport,
    loading,
    error,
    success,
    fetchAllReports,
    fetchReportById,
    fetchReportByDepartment,
    deleteReport,
    clearError: handleClearError,
    resetSuccess: handleResetSuccess,
  };
};
