import { useDispatch, useSelector } from "react-redux";
import { RootState, AppDispatch } from "@/redux/store";
import {
  fetchUsersStart,
  fetchUsersSuccess,
  fetchUserSuccess,
  fetchUsersError,
  createUserStart,
  createUserSuccess,
  createUserError,
  updateUserStart,
  updateUserSuccess,
  updateUserError,
  deleteUserStart,
  deleteUserSuccess,
  deleteUserError,
  setCurrentUser,
  clearError,
  resetSuccess,
} from "./usersSlice";
import { usersAPI } from "./usersAPI";
import { User, CreateUserRequest, UpdateUserRequest } from "@/types/user";

export const useUsers = () => {
  const dispatch = useDispatch<AppDispatch>();
  const { users, currentUser, selectedUser, loading, error, success } = useSelector((state: RootState) => state.users);

  const fetchAllUsers = async () => {
    dispatch(fetchUsersStart());
    try {
      const data = await usersAPI.getAllUsers();
      dispatch(fetchUsersSuccess({ data }));
    } catch (err: any) {
      dispatch(fetchUsersError({ error: err.message }));
    }
  };

  const fetchUserById = async (id: string) => {
    dispatch(fetchUsersStart());
    try {
      const data = await usersAPI.getUserById(id);
      dispatch(fetchUserSuccess({ data }));
    } catch (err: any) {
      dispatch(fetchUsersError({ error: err.message }));
    }
  };

  const createUser = async (userData: CreateUserRequest) => {
    dispatch(createUserStart());
    try {
      const data = await usersAPI.createUser(userData);
      dispatch(createUserSuccess({ data }));
    } catch (err: any) {
      dispatch(createUserError({ error: err.message }));
    }
  };

  const updateUser = async (id: string, userData: UpdateUserRequest) => {
    dispatch(updateUserStart());
    try {
      const data = await usersAPI.updateUser(id, userData);
      dispatch(updateUserSuccess({ data }));
    } catch (err: any) {
      dispatch(updateUserError({ error: err.message }));
    }
  };

  const deleteUser = async (id: string) => {
    dispatch(deleteUserStart());
    try {
      await usersAPI.deleteUser(id);
      dispatch(deleteUserSuccess({ userId: id }));
    } catch (err: any) {
      dispatch(deleteUserError({ error: err.message }));
    }
  };

  const getUserProfile = async () => {
    dispatch(fetchUsersStart());
    try {
      const data = await usersAPI.getUserProfile();
      dispatch(setCurrentUser({ data }));
    } catch (err: any) {
      dispatch(fetchUsersError({ error: err.message }));
    }
  };

  const handleClearError = () => {
    dispatch(clearError());
  };

  const handleResetSuccess = () => {
    dispatch(resetSuccess());
  };

  return {
    users,
    currentUser,
    selectedUser,
    loading,
    error,
    success,
    fetchAllUsers,
    fetchUserById,
    createUser,
    updateUser,
    deleteUser,
    getUserProfile,
    clearError: handleClearError,
    resetSuccess: handleResetSuccess,
  };
};
