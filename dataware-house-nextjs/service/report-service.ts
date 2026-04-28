import { API_END_POINT, TOKEN_COOKIES } from "@/constant/api-end-point";
import { getToken } from "@/lib/init-token";
import axios from "axios";

export async function getAllReportService(query: string) {
  try {
    const resposne = await axios.post(
      `${process.env.API_URL + API_END_POINT.REPORTS}`,
      {},
      {
        params: {
          query: query,
        },
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          Authorization: `Bearer ${getToken(TOKEN_COOKIES.TOKEN_NAME)}`,
        },
      }
    );

    return {
      success: true,
      data: resposne.data.data,
    };
  } catch {
    return {
      success: false,
      data: [],
    };
  }
}

export async function getTotalReportService(query: string) {
  try {
    const resposne = await axios.post(
      `${process.env.API_URL}/reporting/total-pages`,
      {},
      {
        params: {
          query: query,
        },
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          Authorization: `Bearer ${getToken(TOKEN_COOKIES.TOKEN_NAME)}`,
        },
      }
    );

    return {
      success: true,
      data: resposne.data.totalPages,
    };
  } catch {
    return {
      success: false,
      data: 0,
    };
  }
}
