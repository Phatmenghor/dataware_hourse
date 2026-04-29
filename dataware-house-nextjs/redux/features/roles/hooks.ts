import { useDispatch, useSelector } from "react-redux";
import { RootState, AppDispatch } from "@/redux/store";
import {
  fetchRolesStart,
  fetchRolesSuccess,
  fetchRoleSuccess,
  fetchRolesError,
  createRoleStart,
  createRoleSuccess,
  createRoleError,
  updateRoleStart,
  updateRoleSuccess,
  updateRoleError,
  deleteRoleStart,
  deleteRoleSuccess,
  deleteRoleError,
  clearError,
  resetSuccess,
} from "./rolesSlice";
import { rolesAPI } from "./rolesAPI";
import { CreateRoleRequest, UpdateRoleRequest } from "@/types/role";

export const useRoles = () => {
  const dispatch = useDispatch<AppDispatch>();
  const { roles, selectedRole, loading, error, success } = useSelector((state: RootState) => state.roles);

  const fetchAllRoles = async () => {
    dispatch(fetchRolesStart());
    try {
      const data = await rolesAPI.getAllRoles();
      dispatch(fetchRolesSuccess({ data }));
    } catch (err: any) {
      dispatch(fetchRolesError({ error: err.message }));
    }
  };

  const fetchRoleById = async (id: string) => {
    dispatch(fetchRolesStart());
    try {
      const data = await rolesAPI.getRoleById(id);
      dispatch(fetchRoleSuccess({ data }));
    } catch (err: any) {
      dispatch(fetchRolesError({ error: err.message }));
    }
  };

  const createRole = async (data: CreateRoleRequest) => {
    dispatch(createRoleStart());
    try {
      const role = await rolesAPI.createRole(data);
      dispatch(createRoleSuccess({ data: role }));
    } catch (err: any) {
      dispatch(createRoleError({ error: err.message }));
    }
  };

  const updateRole = async (id: string, data: UpdateRoleRequest) => {
    dispatch(updateRoleStart());
    try {
      const role = await rolesAPI.updateRole(id, data);
      dispatch(updateRoleSuccess({ data: role }));
    } catch (err: any) {
      dispatch(updateRoleError({ error: err.message }));
    }
  };

  const deleteRole = async (id: string) => {
    dispatch(deleteRoleStart());
    try {
      await rolesAPI.deleteRole(id);
      dispatch(deleteRoleSuccess({ roleId: id }));
    } catch (err: any) {
      dispatch(deleteRoleError({ error: err.message }));
    }
  };

  const handleClearError = () => {
    dispatch(clearError());
  };

  const handleResetSuccess = () => {
    dispatch(resetSuccess());
  };

  return {
    roles,
    selectedRole,
    loading,
    error,
    success,
    fetchAllRoles,
    fetchRoleById,
    createRole,
    updateRole,
    deleteRole,
    clearError: handleClearError,
    resetSuccess: handleResetSuccess,
  };
};
