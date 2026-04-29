import { useDispatch, useSelector } from "react-redux";
import { RootState, AppDispatch } from "@/redux/store";
import {
  fetchMetadataStart,
  fetchMetadataSuccess,
  fetchMetadataError,
  clearError,
  resetSuccess,
} from "./metadataSlice";
import { metadataAPI } from "./metadataAPI";

export const useMetadata = () => {
  const dispatch = useDispatch<AppDispatch>();
  const { metadata, loading, error, success } = useSelector((state: RootState) => state.metadata);

  const fetchAllMetadata = async () => {
    dispatch(fetchMetadataStart());
    try {
      const data = await metadataAPI.getAllMetadata();
      dispatch(fetchMetadataSuccess({ data }));
    } catch (err: any) {
      dispatch(fetchMetadataError({ error: err.message }));
    }
  };

  const handleClearError = () => {
    dispatch(clearError());
  };

  const handleResetSuccess = () => {
    dispatch(resetSuccess());
  };

  return {
    metadata,
    loading,
    error,
    success,
    fetchAllMetadata,
    clearError: handleClearError,
    resetSuccess: handleResetSuccess,
  };
};
