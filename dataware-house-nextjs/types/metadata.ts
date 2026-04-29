export interface Metadata {
  id: string;
  name: string;
  value?: string;
  type?: string;
  category?: string;
  status?: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface CreateMetadataRequest {
  name: string;
  value?: string;
  type?: string;
  category?: string;
}

export interface UpdateMetadataRequest {
  name?: string;
  value?: string;
  type?: string;
  category?: string;
  status?: string;
}
