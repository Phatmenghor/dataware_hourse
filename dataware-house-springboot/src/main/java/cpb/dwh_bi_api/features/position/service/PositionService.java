package cpb.dwh_bi_api.features.position.service;

import cpb.dwh_bi_api.features.position.dto.request.CreatePositionRequest;
import cpb.dwh_bi_api.features.position.dto.request.UpdatePositionRequest;
import cpb.dwh_bi_api.features.position.dto.response.PositionResponse;

import java.util.List;
import java.util.UUID;

public interface PositionService {
	List<PositionResponse> getAll();
	PositionResponse getById(UUID id);
	PositionResponse create(CreatePositionRequest request);
	PositionResponse update(UUID id, UpdatePositionRequest request);
	void delete(UUID id);
}
