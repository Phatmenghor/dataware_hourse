package cpb.dwh_bi_api.features.widget.service;

import cpb.dwh_bi_api.features.widget.dto.request.CreateWidgetRequest;
import cpb.dwh_bi_api.features.widget.dto.request.UpdateWidgetRequest;
import cpb.dwh_bi_api.features.widget.dto.response.WidgetResponse;

import java.util.List;
import java.util.UUID;

public interface WidgetService {
	List<WidgetResponse> getAll();
	WidgetResponse getById(UUID id);
	WidgetResponse create(CreateWidgetRequest request);
	WidgetResponse update(UUID id, UpdateWidgetRequest request);
	void delete(UUID id);
}
