package cpb.dwh_bi_api.mappers;

import cpb.dwh_bi_api.dto.request.CreateUnitRequest;
import cpb.dwh_bi_api.dto.response.UnitResponse;
import cpb.dwh_bi_api.entities.Unit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UnitMapper {

	@Mapping(source = "department.id", target = "departmentId")
	@Mapping(source = "department.name", target = "departmentName")
	UnitResponse toResponse(Unit entity);

	Unit toEntity(CreateUnitRequest request);
}
