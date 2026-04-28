package cpb.dwh_bi_api.services;

import cpb.dwh_bi_api.dto.response.WidgetResponse;
import java.util.List;
import java.util.UUID;

public interface WidgetService {
	List<WidgetResponse> getAll();
	WidgetResponse getById(UUID id);
	void delete(UUID id);
}
