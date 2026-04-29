package cpb.dwh_bi_api.features.role.service;

import cpb.dwh_bi_api.features.role.dto.request.CreateRoleRequest;
import cpb.dwh_bi_api.features.role.dto.request.UpdateRoleRequest;
import cpb.dwh_bi_api.features.role.dto.response.RoleResponse;

import java.util.List;
import java.util.UUID;

public interface RoleService {
	List<RoleResponse> getAll();
	RoleResponse getById(UUID id);
	RoleResponse create(CreateRoleRequest request);
	RoleResponse update(UUID id, UpdateRoleRequest request);
	void delete(UUID id);
}
