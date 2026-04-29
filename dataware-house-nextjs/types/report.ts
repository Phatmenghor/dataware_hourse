export interface Report {
  id: string;
  name: string;
  description?: string;
  department?: string;
  status?: string;
  columns?: ReportColumn[];
  roles?: string[];
  parameters?: ReportParameter[];
  createdAt?: string;
  updatedAt?: string;
}

export interface ReportColumn {
  id: string;
  name: string;
  field?: string;
  type?: string;
  visible?: boolean;
  order?: number;
}

export interface ReportParameter {
  id: string;
  name: string;
  type?: string;
  value?: any;
  required?: boolean;
}

export interface CreateReportRequest {
  name: string;
  description?: string;
  department?: string;
}

export interface UpdateReportRequest {
  name?: string;
  description?: string;
  department?: string;
  status?: string;
}
