import { Widget } from "@/types/widget";

export interface WidgetsState {
  widgets: Widget[];
  selectedWidget: Widget | null;
  loading: boolean;
  error: string | null;
  success: boolean;
}

export interface FetchWidgetsPayload {
  data: Widget[];
}

export interface SetErrorPayload {
  error: string;
}
