export interface Department {
  id: string;
  name: string;
  short_name: string;
  code: string;
  type: 'HO' | 'Branch';
  status?: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface CreateDepartmentRequest {
  name: string;
  short_name: string;
  code: string;
  type: 'HO' | 'Branch';
}

export interface UpdateDepartmentRequest {
  name?: string;
  short_name?: string;
  code?: string;
  type?: 'HO' | 'Branch';
  status?: string;
}
