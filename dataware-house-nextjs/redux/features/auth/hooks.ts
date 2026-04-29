import { useDispatch, useSelector } from "react-redux";
import { RootState, AppDispatch } from "@/redux/store";
import {
  loginStart,
  loginSuccess,
  loginError,
  logoutStart,
  logoutSuccess,
  logoutError,
  registerStart,
  registerSuccess,
  registerError,
  refreshTokenStart,
  refreshTokenSuccess,
  refreshTokenError,
  setUser,
  clearError,
  resetSuccess,
} from "./authSlice";
import { authAPI } from "./authAPI";
import { LoginRequest, RegisterRequest, RefreshTokenRequest } from "./types";

export const useAuth = () => {
  const dispatch = useDispatch<AppDispatch>();
  const { isAuthenticated, user, token, loading, error, success } = useSelector((state: RootState) => state.auth);

  const login = async (credentials: LoginRequest) => {
    dispatch(loginStart());
    try {
      const response = await authAPI.login(credentials);
      dispatch(loginSuccess({
        user: response.user,
        token: response.token,
      }));
    } catch (err: any) {
      dispatch(loginError({ error: err.message }));
    }
  };

  const register = async (data: RegisterRequest) => {
    dispatch(registerStart());
    try {
      const response = await authAPI.register(data);
      dispatch(registerSuccess({
        user: response.user,
        token: response.token,
      }));
    } catch (err: any) {
      dispatch(registerError({ error: err.message }));
    }
  };

  const logout = async () => {
    dispatch(logoutStart());
    try {
      await authAPI.logout();
      dispatch(logoutSuccess());
    } catch (err: any) {
      dispatch(logoutError({ error: err.message }));
    }
  };

  const refreshToken = async (request: RefreshTokenRequest) => {
    dispatch(refreshTokenStart());
    try {
      const response = await authAPI.refreshToken(request);
      dispatch(refreshTokenSuccess({ token: response.token }));
    } catch (err: any) {
      dispatch(refreshTokenError({ error: err.message }));
    }
  };

  const handleSetUser = (userData: any) => {
    dispatch(setUser({ user: userData }));
  };

  const handleClearError = () => {
    dispatch(clearError());
  };

  const handleResetSuccess = () => {
    dispatch(resetSuccess());
  };

  return {
    isAuthenticated,
    user,
    token,
    loading,
    error,
    success,
    login,
    register,
    logout,
    refreshToken,
    setUser: handleSetUser,
    clearError: handleClearError,
    resetSuccess: handleResetSuccess,
  };
};
