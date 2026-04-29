import { Report } from "@/types/report";

export interface ReportsState {
  reports: Report[];
  selectedReport: Report | null;
  loading: boolean;
  error: string | null;
  success: boolean;
}

export interface FetchReportsPayload {
  data: Report[];
}

export interface FetchReportPayload {
  data: Report;
}

export interface CreateReportPayload {
  data: Report;
}

export interface UpdateReportPayload {
  data: Report;
}

export interface DeleteReportPayload {
  reportId: string;
}

export interface SetErrorPayload {
  error: string;
}
