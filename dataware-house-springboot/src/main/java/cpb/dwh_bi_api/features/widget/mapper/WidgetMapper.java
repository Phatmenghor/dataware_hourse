package cpb.dwh_bi_api.features.widget.mapper;

import cpb.dwh_bi_api.features.widget.dto.request.CreateWidgetRequest;
import cpb.dwh_bi_api.features.widget.dto.request.UpdateWidgetRequest;
import cpb.dwh_bi_api.features.widget.dto.response.WidgetResponse;
import cpb.dwh_bi_api.features.widget.models.Widget;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WidgetMapper {

	WidgetResponse toResponse(Widget entity);

	Widget toEntity(CreateWidgetRequest request);

	void updateEntity(UpdateWidgetRequest request, @MappingTarget Widget entity);
}
