package cpb.dwh_bi_api.services;

import cpb.dwh_bi_api.dto.request.CreateDepartmentRequest;
import cpb.dwh_bi_api.dto.request.UpdateDepartmentRequest;
import cpb.dwh_bi_api.dto.response.DepartmentResponse;
import java.util.List;
import java.util.UUID;

public interface DepartmentService {
	List<DepartmentResponse> getAll();
	DepartmentResponse getById(UUID id);
	DepartmentResponse create(CreateDepartmentRequest request);
	DepartmentResponse update(UUID id, UpdateDepartmentRequest request);
	void delete(UUID id);
}
