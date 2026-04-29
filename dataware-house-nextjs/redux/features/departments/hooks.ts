import { useDispatch, useSelector } from "react-redux";
import { RootState, AppDispatch } from "@/redux/store";
import {
  fetchDepartmentsStart,
  fetchDepartmentsSuccess,
  fetchDepartmentSuccess,
  fetchDepartmentsError,
  createDepartmentStart,
  createDepartmentSuccess,
  createDepartmentError,
  updateDepartmentStart,
  updateDepartmentSuccess,
  updateDepartmentError,
  deleteDepartmentStart,
  deleteDepartmentSuccess,
  deleteDepartmentError,
  clearError,
  resetSuccess,
} from "./departmentsSlice";
import { departmentsAPI } from "./departmentsAPI";
import { CreateDepartmentRequest, UpdateDepartmentRequest } from "@/types/department";

export const useDepartments = () => {
  const dispatch = useDispatch<AppDispatch>();
  const { departments, selectedDepartment, loading, error, success } = useSelector((state: RootState) => state.departments);

  const fetchAllDepartments = async () => {
    dispatch(fetchDepartmentsStart());
    try {
      const data = await departmentsAPI.getAllDepartments();
      dispatch(fetchDepartmentsSuccess({ data }));
    } catch (err: any) {
      dispatch(fetchDepartmentsError({ error: err.message }));
    }
  };

  const fetchDepartmentById = async (id: string) => {
    dispatch(fetchDepartmentsStart());
    try {
      const data = await departmentsAPI.getDepartmentById(id);
      dispatch(fetchDepartmentSuccess({ data }));
    } catch (err: any) {
      dispatch(fetchDepartmentsError({ error: err.message }));
    }
  };

  const createDepartment = async (data: CreateDepartmentRequest) => {
    dispatch(createDepartmentStart());
    try {
      const department = await departmentsAPI.createDepartment(data);
      dispatch(createDepartmentSuccess({ data: department }));
    } catch (err: any) {
      dispatch(createDepartmentError({ error: err.message }));
    }
  };

  const updateDepartment = async (id: string, data: UpdateDepartmentRequest) => {
    dispatch(updateDepartmentStart());
    try {
      const department = await departmentsAPI.updateDepartment(id, data);
      dispatch(updateDepartmentSuccess({ data: department }));
    } catch (err: any) {
      dispatch(updateDepartmentError({ error: err.message }));
    }
  };

  const deleteDepartment = async (id: string) => {
    dispatch(deleteDepartmentStart());
    try {
      await departmentsAPI.deleteDepartment(id);
      dispatch(deleteDepartmentSuccess({ departmentId: id }));
    } catch (err: any) {
      dispatch(deleteDepartmentError({ error: err.message }));
    }
  };

  const handleClearError = () => {
    dispatch(clearError());
  };

  const handleResetSuccess = () => {
    dispatch(resetSuccess());
  };

  return {
    departments,
    selectedDepartment,
    loading,
    error,
    success,
    fetchAllDepartments,
    fetchDepartmentById,
    createDepartment,
    updateDepartment,
    deleteDepartment,
    clearError: handleClearError,
    resetSuccess: handleResetSuccess,
  };
};
