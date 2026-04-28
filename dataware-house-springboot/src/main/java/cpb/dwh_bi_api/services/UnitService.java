package cpb.dwh_bi_api.services;

import cpb.dwh_bi_api.dto.request.CreateUnitRequest;
import cpb.dwh_bi_api.dto.response.UnitResponse;
import java.util.List;
import java.util.UUID;

public interface UnitService {
	List<UnitResponse> getAll();
	List<UnitResponse> getByDepartmentId(UUID departmentId);
	UnitResponse getById(UUID id);
	UnitResponse create(CreateUnitRequest request);
	UnitResponse update(UUID id, CreateUnitRequest request);
	void delete(UUID id);
}
