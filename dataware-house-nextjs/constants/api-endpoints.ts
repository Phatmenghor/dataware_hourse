const BACKEND_URL = process.env.NEXT_PUBLIC_BACKEND_URL || "http://localhost:8081/api/v1";

export const API_END_POINT = {
  AUTH: `${BACKEND_URL}/auth`,
  PROFILE: `${BACKEND_URL}/users/profile`,
  USERS: `${BACKEND_URL}/users`,
  DEPARTMENTS: `${BACKEND_URL}/departments`,
  POSITIONS: `${BACKEND_URL}/positions`,
  ROLES: `${BACKEND_URL}/roles`,
  REPORTS: `${BACKEND_URL}/reports`,
  WIDGETS: `${BACKEND_URL}/widgets`,
  UNITS: `${BACKEND_URL}/units`,
  METADATA: `${BACKEND_URL}/metadata`,
};

export const TOKEN_COOKIES = {
  TOKEN_NAME: "jwtToken",
  AUTH_ID: "authId",
};
