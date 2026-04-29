import { User, CreateUserRequest, UpdateUserRequest, UserProfile } from "@/types/user";

export interface UsersState {
  users: User[];
  currentUser: UserProfile | null;
  selectedUser: User | null;
  loading: boolean;
  error: string | null;
  success: boolean;
}

export interface FetchUsersPayload {
  data: User[];
}

export interface FetchUserPayload {
  data: User;
}

export interface CreateUserPayload {
  data: User;
}

export interface UpdateUserPayload {
  data: User;
}

export interface DeleteUserPayload {
  userId: string;
}

export interface SetErrorPayload {
  error: string;
}
