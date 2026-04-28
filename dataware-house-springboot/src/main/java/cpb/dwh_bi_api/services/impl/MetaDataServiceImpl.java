package cpb.dwh_bi_api.services.impl;
import cpb.dwh_bi_api.exceptions.ResourceNotFoundException;

import cpb.dwh_bi_api.dto.response.MetaDataResponse;
import cpb.dwh_bi_api.entities.MetaData;
import cpb.dwh_bi_api.mappers.MetaDataMapper;
import cpb.dwh_bi_api.repositories.MetaDataRepository;
import cpb.dwh_bi_api.services.MetaDataService;
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
public class MetaDataServiceImpl implements MetaDataService {

	private final MetaDataRepository metaDataRepository;
	private final MetaDataMapper metaDataMapper;

	@Override
	@Transactional(readOnly = true)
	public List<MetaDataResponse> getAll() {
		log.info("Fetching all metadata");
		return metaDataRepository.findAll()
			.stream()
			.map(metaDataMapper::toResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<MetaDataResponse> getByType(String type) {
		log.info("Fetching metadata by type: {}", type);
		return metaDataRepository.findByType(type)
			.stream()
			.map(metaDataMapper::toResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public MetaDataResponse getById(UUID id) {
		log.info("Fetching metadata by id: {}", id);
		MetaData metaData = metaDataRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("MetaData not found with id: " + id));
		return metaDataMapper.toResponse(metaData);
	}

	@Override
	public void delete(UUID id) {
		log.info("Deleting metadata with id: {}", id);
		if (!metaDataRepository.existsById(id)) {
			throw new cpb.dwh_bi_api.exceptions.ResourceNotFoundException("MetaData not found with id: " + id);
		}
		metaDataRepository.deleteById(id);
	}
}
