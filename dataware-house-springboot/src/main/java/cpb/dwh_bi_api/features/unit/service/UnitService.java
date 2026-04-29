package cpb.dwh_bi_api.features.unit.service;

import cpb.dwh_bi_api.features.unit.dto.request.CreateUnitRequest;
import cpb.dwh_bi_api.features.unit.dto.request.UpdateUnitRequest;
import cpb.dwh_bi_api.features.unit.dto.response.UnitResponse;

import java.util.List;
import java.util.UUID;

public interface UnitService {
	List<UnitResponse> getAll();
	UnitResponse getById(UUID id);
	UnitResponse create(CreateUnitRequest request);
	UnitResponse update(UUID id, UpdateUnitRequest request);
	void delete(UUID id);
}
