package cpb.dwh_bi_api.features.metadata.service;

import cpb.dwh_bi_api.features.metadata.dto.request.CreateMetaDataRequest;
import cpb.dwh_bi_api.features.metadata.dto.request.UpdateMetaDataRequest;
import cpb.dwh_bi_api.features.metadata.dto.response.MetaDataResponse;

import java.util.List;
import java.util.UUID;

public interface MetaDataService {
	List<MetaDataResponse> getAll();
	MetaDataResponse getById(UUID id);
	MetaDataResponse create(CreateMetaDataRequest request);
	MetaDataResponse update(UUID id, UpdateMetaDataRequest request);
	void delete(UUID id);
}
