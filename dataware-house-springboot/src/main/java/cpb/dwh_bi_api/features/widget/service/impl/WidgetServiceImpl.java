package cpb.dwh_bi_api.features.widget.service.impl;

import cpb.dwh_bi_api.features.widget.dto.request.CreateWidgetRequest;
import cpb.dwh_bi_api.features.widget.dto.request.UpdateWidgetRequest;
import cpb.dwh_bi_api.features.widget.dto.response.WidgetResponse;
import cpb.dwh_bi_api.features.widget.mapper.WidgetMapper;
import cpb.dwh_bi_api.features.widget.models.Widget;
import cpb.dwh_bi_api.features.widget.repository.WidgetRepository;
import cpb.dwh_bi_api.features.widget.service.WidgetService;
import cpb.dwh_bi_api.shared.exception.ResourceNotFoundException;
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
			.orElseThrow(() -> new ResourceNotFoundException("Widget not found with id: " + id));
		return widgetMapper.toResponse(widget);
	}

	@Override
	public WidgetResponse create(CreateWidgetRequest request) {
		log.info("Creating new widget: {}", request.getName());
		Widget widget = widgetMapper.toEntity(request);
		Widget saved = widgetRepository.save(widget);
		return widgetMapper.toResponse(saved);
	}

	@Override
	public WidgetResponse update(UUID id, UpdateWidgetRequest request) {
		log.info("Updating widget with id: {}", id);
		Widget widget = widgetRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Widget not found with id: " + id));
		widgetMapper.updateEntity(request, widget);
		Widget updated = widgetRepository.save(widget);
		return widgetMapper.toResponse(updated);
	}

	@Override
	public void delete(UUID id) {
		log.info("Deleting widget with id: {}", id);
		if (!widgetRepository.existsById(id)) {
			throw new ResourceNotFoundException("Widget not found with id: " + id);
		}
		widgetRepository.deleteById(id);
	}
}
