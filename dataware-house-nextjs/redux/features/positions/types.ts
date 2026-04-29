import { Position } from "@/types/position";

export interface PositionsState {
  positions: Position[];
  selectedPosition: Position | null;
  loading: boolean;
  error: string | null;
  success: boolean;
}

export interface FetchPositionsPayload {
  data: Position[];
}

export interface FetchPositionPayload {
  data: Position;
}

export interface CreatePositionPayload {
  data: Position;
}

export interface UpdatePositionPayload {
  data: Position;
}

export interface DeletePositionPayload {
  positionId: string;
}

export interface SetErrorPayload {
  error: string;
}
