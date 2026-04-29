export interface Unit {
  id: string;
  name: string;
  code?: string;
  department?: string;
  status?: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface CreateUnitRequest {
  name: string;
  code?: string;
  department?: string;
}

export interface UpdateUnitRequest {
  name?: string;
  code?: string;
  department?: string;
  status?: string;
}
