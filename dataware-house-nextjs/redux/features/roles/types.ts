import { Role } from "@/types/role";

export interface RolesState {
  roles: Role[];
  selectedRole: Role | null;
  loading: boolean;
  error: string | null;
  success: boolean;
}

export interface FetchRolesPayload {
  data: Role[];
}

export interface FetchRolePayload {
  data: Role;
}

export interface CreateRolePayload {
  data: Role;
}

export interface UpdateRolePayload {
  data: Role;
}

export interface DeleteRolePayload {
  roleId: string;
}

export interface SetErrorPayload {
  error: string;
}
