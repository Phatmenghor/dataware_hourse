export interface User {
  id: string;
  email: string;
  firstName?: string;
  lastName?: string;
  name?: string;
  status?: string;
  role?: string;
  department?: string;
  position?: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface CreateUserRequest {
  email: string;
  firstName?: string;
  lastName?: string;
  password?: string;
  status?: string;
  role?: string;
  department?: string;
  position?: string;
}

export interface UpdateUserRequest {
  email?: string;
  firstName?: string;
  lastName?: string;
  status?: string;
  role?: string;
  department?: string;
  position?: string;
}

export interface UserProfile extends User {
  permissions?: string[];
  metadata?: Record<string, any>;
}
