package cpb.dwh_bi_api.services;

import cpb.dwh_bi_api.dto.response.MetaDataResponse;
import java.util.List;
import java.util.UUID;

public interface MetaDataService {
	List<MetaDataResponse> getAll();
	List<MetaDataResponse> getByType(String type);
	MetaDataResponse getById(UUID id);
	void delete(UUID id);
}
