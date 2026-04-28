package cpb.dwh_bi_api.mappers;

import cpb.dwh_bi_api.dto.response.WidgetResponse;
import cpb.dwh_bi_api.entities.Widget;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WidgetMapper {

	WidgetResponse toResponse(Widget entity);

	Widget toEntity(WidgetResponse response);
}
