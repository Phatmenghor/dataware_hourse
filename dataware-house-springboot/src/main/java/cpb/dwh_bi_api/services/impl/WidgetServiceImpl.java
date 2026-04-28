package cpb.dwh_bi_api.services.impl;
import cpb.dwh_bi_api.exceptions.ResourceNotFoundException;

import cpb.dwh_bi_api.dto.response.WidgetResponse;
import cpb.dwh_bi_api.entities.Widget;
import cpb.dwh_bi_api.mappers.WidgetMapper;
import cpb.dwh_bi_api.repositories.WidgetRepository;
import cpb.dwh_bi_api.services.WidgetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class WidgetServiceImpl implements WidgetService {

	private final WidgetRepository widgetRepository;
	private final WidgetMapper widgetMapper;

	@Override
	@Transactional(readOnly = true)
	public List<WidgetResponse> getAll() {
		log.info("Fetching all widgets");
		return widgetRepository.findAll()
			.stream()
			.map(widgetMapper::toResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public WidgetResponse getById(UUID id) {
		log.info("Fetching widget by id: {}", id);
		Widget widget = widgetRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Widget not found with id: " + id));
		return widgetMapper.toResponse(widget);
	}

	@Override
	public void delete(UUID id) {
		log.info("Deleting widget with id: {}", id);
		if (!widgetRepository.existsById(id)) {
			throw new cpb.dwh_bi_api.exceptions.ResourceNotFoundException("Widget not found with id: " + id);
		}
		widgetRepository.deleteById(id);
	}
}
