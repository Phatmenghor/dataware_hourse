import { Unit } from "@/types/unit";

export interface UnitsState {
  units: Unit[];
  selectedUnit: Unit | null;
  loading: boolean;
  error: string | null;
  success: boolean;
}

export interface FetchUnitsPayload {
  data: Unit[];
}

export interface SetErrorPayload {
  error: string;
}
