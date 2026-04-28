package cpb.dwh_bi_api.mappers;

import cpb.dwh_bi_api.dto.request.CreateUserRequest;
import cpb.dwh_bi_api.dto.request.UpdateUserRequest;
import cpb.dwh_bi_api.dto.response.UserResponse;
import cpb.dwh_bi_api.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

	@Mapping(source = "department.id", target = "departmentId")
	@Mapping(source = "department.name", target = "departmentName")
	@Mapping(source = "position.id", target = "positionId")
	@Mapping(source = "position.name", target = "positionName")
	@Mapping(source = "role.id", target = "roleId")
	@Mapping(source = "role.name", target = "roleName")
	UserResponse toResponse(User entity);

	User toEntity(CreateUserRequest request);

	void updateEntity(UpdateUserRequest request, @MappingTarget User entity);
}
