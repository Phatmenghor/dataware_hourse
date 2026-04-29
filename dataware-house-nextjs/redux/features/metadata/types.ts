import { Metadata } from "@/types/metadata";

export interface MetadataState {
  metadata: Metadata[];
  selectedMetadata: Metadata | null;
  loading: boolean;
  error: string | null;
  success: boolean;
}

export interface FetchMetadataPayload {
  data: Metadata[];
}

export interface SetErrorPayload {
  error: string;
}
