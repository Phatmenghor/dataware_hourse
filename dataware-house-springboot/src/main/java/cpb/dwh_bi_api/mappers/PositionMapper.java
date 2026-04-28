package cpb.dwh_bi_api.mappers;

import cpb.dwh_bi_api.dto.request.CreatePositionRequest;
import cpb.dwh_bi_api.dto.request.UpdatePositionRequest;
import cpb.dwh_bi_api.dto.response.PositionResponse;
import cpb.dwh_bi_api.entities.Position;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PositionMapper {

	PositionResponse toResponse(Position entity);

	Position toEntity(CreatePositionRequest request);

	void updateEntity(UpdatePositionRequest request, @MappingTarget Position entity);
}
