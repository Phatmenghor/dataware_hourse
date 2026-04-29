package cpb.dwh_bi_api.features.department.service;

import cpb.dwh_bi_api.features.department.dto.request.CreateDepartmentRequest;
import cpb.dwh_bi_api.features.department.dto.request.UpdateDepartmentRequest;
import cpb.dwh_bi_api.features.department.dto.response.DepartmentResponse;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
	List<DepartmentResponse> getAll();
	DepartmentResponse getById(UUID id);
	DepartmentResponse create(CreateDepartmentRequest request);
	DepartmentResponse update(UUID id, UpdateDepartmentRequest request);
	void delete(UUID id);
}
