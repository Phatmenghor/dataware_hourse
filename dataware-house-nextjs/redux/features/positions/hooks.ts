import { useDispatch, useSelector } from "react-redux";
import { RootState, AppDispatch } from "@/redux/store";
import {
  fetchPositionsStart,
  fetchPositionsSuccess,
  fetchPositionSuccess,
  fetchPositionsError,
  createPositionStart,
  createPositionSuccess,
  createPositionError,
  updatePositionStart,
  updatePositionSuccess,
  updatePositionError,
  deletePositionStart,
  deletePositionSuccess,
  deletePositionError,
  clearError,
  resetSuccess,
} from "./positionsSlice";
import { positionsAPI } from "./positionsAPI";
import { CreatePositionRequest, UpdatePositionRequest } from "@/types/position";

export const usePositions = () => {
  const dispatch = useDispatch<AppDispatch>();
  const { positions, selectedPosition, loading, error, success } = useSelector((state: RootState) => state.positions);

  const fetchAllPositions = async () => {
    dispatch(fetchPositionsStart());
    try {
      const data = await positionsAPI.getAllPositions();
      dispatch(fetchPositionsSuccess({ data }));
    } catch (err: any) {
      dispatch(fetchPositionsError({ error: err.message }));
    }
  };

  const fetchPositionById = async (id: string) => {
    dispatch(fetchPositionsStart());
    try {
      const data = await positionsAPI.getPositionById(id);
      dispatch(fetchPositionSuccess({ data }));
    } catch (err: any) {
      dispatch(fetchPositionsError({ error: err.message }));
    }
  };

  const createPosition = async (data: CreatePositionRequest) => {
    dispatch(createPositionStart());
    try {
      const position = await positionsAPI.createPosition(data);
      dispatch(createPositionSuccess({ data: position }));
    } catch (err: any) {
      dispatch(createPositionError({ error: err.message }));
    }
  };

  const updatePosition = async (id: string, data: UpdatePositionRequest) => {
    dispatch(updatePositionStart());
    try {
      const position = await positionsAPI.updatePosition(id, data);
      dispatch(updatePositionSuccess({ data: position }));
    } catch (err: any) {
      dispatch(updatePositionError({ error: err.message }));
    }
  };

  const deletePosition = async (id: string) => {
    dispatch(deletePositionStart());
    try {
      await positionsAPI.deletePosition(id);
      dispatch(deletePositionSuccess({ positionId: id }));
    } catch (err: any) {
      dispatch(deletePositionError({ error: err.message }));
    }
  };

  const handleClearError = () => {
    dispatch(clearError());
  };

  const handleResetSuccess = () => {
    dispatch(resetSuccess());
  };

  return {
    positions,
    selectedPosition,
    loading,
    error,
    success,
    fetchAllPositions,
    fetchPositionById,
    createPosition,
    updatePosition,
    deletePosition,
    clearError: handleClearError,
    resetSuccess: handleResetSuccess,
  };
};
