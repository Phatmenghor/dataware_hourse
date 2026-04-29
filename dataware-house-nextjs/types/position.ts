export interface Position {
  id: string;
  name: string;
  code?: string;
  description?: string;
  department?: string;
  status?: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface CreatePositionRequest {
  name: string;
  code?: string;
  description?: string;
  department?: string;
}

export interface UpdatePositionRequest {
  name?: string;
  code?: string;
  description?: string;
  department?: string;
  status?: string;
}
