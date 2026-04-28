import apiClient from "@/lib/api-client";

export async function getAllReportsService() {
  try {
    const response = await apiClient.get("/reports");
    return {
      success: true,
      data: response.data || [],
    };
  } catch (error: any) {
    console.error("Error fetching reports:", error.message);
    return {
      success: false,
      data: [],
      error: error.message,
    };
  }
}

export async function getReportByDepartmentService(departmentId: string) {
  try {
    const response = await apiClient.get(`/reports/department/${departmentId}`);
    return {
      success: true,
      data: response.data || [],
    };
  } catch (error: any) {
    console.error("Error fetching reports by department:", error.message);
    return {
      success: false,
      data: [],
      error: error.message,
    };
  }
}

export async function getReportByIdService(id: string) {
  try {
    const response = await apiClient.get(`/reports/${id}`);
    return {
      success: true,
      data: response.data,
    };
  } catch (error: any) {
    console.error("Error fetching report:", error.message);
    return {
      success: false,
      data: null,
      error: error.message,
    };
  }
}

export async function deleteReportService(id: string) {
  try {
    await apiClient.delete(`/reports/${id}`);
    return {
      success: true,
    };
  } catch (error: any) {
    console.error("Error deleting report:", error.message);
    return {
      success: false,
      error: error.message,
    };
  }
}
