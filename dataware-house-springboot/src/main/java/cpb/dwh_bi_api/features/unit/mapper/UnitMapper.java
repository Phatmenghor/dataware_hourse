package cpb.dwh_bi_api.features.unit.mapper;

import cpb.dwh_bi_api.features.unit.dto.request.CreateUnitRequest;
import cpb.dwh_bi_api.features.unit.dto.request.UpdateUnitRequest;
import cpb.dwh_bi_api.features.unit.dto.response.UnitResponse;
import cpb.dwh_bi_api.features.unit.models.Unit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UnitMapper {

	@Mapping(source = "department.id", target = "departmentId")
	UnitResponse toResponse(Unit entity);

	Unit toEntity(CreateUnitRequest request);

	void updateEntity(UpdateUnitRequest request, @MappingTarget Unit entity);
}
