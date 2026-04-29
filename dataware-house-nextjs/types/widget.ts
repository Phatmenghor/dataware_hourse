export interface Widget {
  id: string;
  name: string;
  description?: string;
  type?: string;
  status?: string;
  labels?: WidgetLabel[];
  roles?: string[];
  parameters?: WidgetParameter[];
  createdAt?: string;
  updatedAt?: string;
}

export interface WidgetLabel {
  id: string;
  name: string;
  value?: string;
}

export interface WidgetParameter {
  id: string;
  name: string;
  type?: string;
  value?: any;
  required?: boolean;
}

export interface CreateWidgetRequest {
  name: string;
  description?: string;
  type?: string;
}

export interface UpdateWidgetRequest {
  name?: string;
  description?: string;
  type?: string;
  status?: string;
}
