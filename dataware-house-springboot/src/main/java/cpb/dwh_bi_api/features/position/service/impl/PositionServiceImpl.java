package cpb.dwh_bi_api.features.position.service.impl;

import cpb.dwh_bi_api.features.position.dto.request.CreatePositionRequest;
import cpb.dwh_bi_api.features.position.dto.request.UpdatePositionRequest;
import cpb.dwh_bi_api.features.position.dto.response.PositionResponse;
import cpb.dwh_bi_api.features.position.mapper.PositionMapper;
import cpb.dwh_bi_api.features.position.models.Position;
import cpb.dwh_bi_api.features.position.repository.PositionRepository;
import cpb.dwh_bi_api.features.position.service.PositionService;
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
public class PositionServiceImpl implements PositionService {

	private final PositionRepository positionRepository;
	private final PositionMapper positionMapper;

	@Override
	@Transactional(readOnly = true)
	public List<PositionResponse> getAll() {
		log.info("Fetching all positions");
		return positionRepository.findAll()
			.stream()
			.map(positionMapper::toResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public PositionResponse getById(UUID id) {
		log.info("Fetching position by id: {}", id);
		Position position = positionRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + id));
		return positionMapper.toResponse(position);
	}

	@Override
	public PositionResponse create(CreatePositionRequest request) {
		log.info("Creating new position: {}", request.getName());
		Position position = positionMapper.toEntity(request);
		Position saved = positionRepository.save(position);
		return positionMapper.toResponse(saved);
	}

	@Override
	public PositionResponse update(UUID id, UpdatePositionRequest request) {
		log.info("Updating position with id: {}", id);
		Position position = positionRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + id));
		positionMapper.updateEntity(request, position);
		Position updated = positionRepository.save(position);
		return positionMapper.toResponse(updated);
	}

	@Override
	public void delete(UUID id) {
		log.info("Deleting position with id: {}", id);
		if (!positionRepository.existsById(id)) {
			throw new ResourceNotFoundException("Position not found with id: " + id);
		}
		positionRepository.deleteById(id);
	}
}
