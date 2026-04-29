package cpb.dwh_bi_api.features.user.service;

import cpb.dwh_bi_api.features.user.dto.request.CreateUserRequest;
import cpb.dwh_bi_api.features.user.dto.request.UpdateUserRequest;
import cpb.dwh_bi_api.features.user.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
	List<UserResponse> getAll();
	UserResponse getById(UUID id);
	UserResponse getByUsername(String username);
	UserResponse create(CreateUserRequest request);
	UserResponse update(UUID id, UpdateUserRequest request);
	void delete(UUID id);
	UserResponse resetPassword(UUID id, String newPassword);
	UserResponse getProfile(String username);
}
