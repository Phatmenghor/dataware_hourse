package cpb.dwh_bi_api.result_set;

import cpb.dwh_bi_api.models.DepartmentModel;
import cpb.dwh_bi_api.models.PositionModel;
import cpb.dwh_bi_api.models.RoleModel;
import cpb.dwh_bi_api.models.UserModel;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserResultSet {

    public List<UserModel> getAll(ResultSet result){

        List<UserModel> users = new ArrayList<>();

        try {

            while (result.next()){
                DepartmentModel department = new DepartmentModel();
                PositionModel position = new PositionModel();
                RoleModel role = new RoleModel();
                UserModel user = new UserModel();

                department.setId(result.getString("department_id"));
                department.setName(result.getString("department_name"));
                department.setShort_name(result.getString("department_short_name"));
                department.setCode(result.getString("department_code"));
                department.setStatus(result.getBoolean("department_status"));
                position.setId(result.getString("position_id"));
                position.setName(result.getString("position_name"));
                position.setCode(result.getString("position_code"));
                position.setStatus(result.getBoolean("position_status"));
                role.setId(result.getString("role_id"));
                role.setName(result.getString("role_name"));
                role.setCode(result.getString("role_code"));
                role.setStatus(result.getBoolean("role_status"));
                user.setId(UUID.fromString(result.getString("id")));
                user.setFull_name(result.getString("full_name"));
                user.setStaff_id(result.getString("staff_id"));
                user.setPassword(result.getString("password"));
                user.setUsername(result.getString("username"));
                user.setDepartment_id(result.getString("department_id"));
                user.setPosition_id(result.getString("position_id"));
                user.setRole_id(result.getString("role_id"));
                user.setStatus(result.getBoolean("status"));
                user.setDepartment_name(result.getString("department_name"));
                user.setPosition_name(result.getString("position_name"));
                user.setRole_name(result.getString("role_name"));
                user.setDepartment(department);
                user.setPosition(position);
                user.setRole(role);
                users.add(user);
            }

        }catch (Exception e){

        }
        return users;
    }

    public UserModel getOne(ResultSet result){
        UserModel user = new UserModel();
        try {

            while (result.next()){
                DepartmentModel department = new DepartmentModel();
                PositionModel position = new PositionModel();
                RoleModel role = new RoleModel();
                department.setId(result.getString("department_id"));
                department.setName(result.getString("department_name"));
                department.setShort_name(result.getString("department_short_name"));
                department.setCode(result.getString("department_code"));
                department.setStatus(result.getBoolean("department_status"));
                position.setId(result.getString("position_id"));
                position.setName(result.getString("position_name"));
                position.setCode(result.getString("position_code"));
                position.setStatus(result.getBoolean("position_status"));
                role.setId(result.getString("role_id"));
                role.setName(result.getString("role_name"));
                role.setCode(result.getString("role_code"));
                role.setStatus(result.getBoolean("role_status"));
                user.setId(UUID.fromString(result.getString("id")));
                user.setFull_name(result.getString("full_name"));
                user.setStaff_id(result.getString("staff_id"));
                user.setPassword(result.getString("password"));
                user.setUsername(result.getString("username"));
                user.setDepartment_id(result.getString("department_id"));
                user.setPosition_id(result.getString("position_id"));
                user.setRole_id(result.getString("role_id"));
                user.setStatus(result.getBoolean("status"));
                user.setDepartment_name(result.getString("department_name"));
                user.setPosition_name(result.getString("position_name"));
                user.setRole_name(result.getString("role_name"));
                user.setDepartment(department);
                user.setPosition(position);
                user.setRole(role);

            }

        }catch (Exception e){

        }


        return user;
    }

    public UserModel getOneAD(ResultSet result){
        UserModel user = new UserModel();
        try {

            while (result.next()){
                user.setId(UUID.fromString(result.getString("id")));
                user.setFull_name(result.getString("full_name"));
                user.setUsername(result.getString("username"));
                user.setDepartment_id(result.getString("department_id"));
            }

        }catch (Exception e){

        }


        return user;
    }

    public UserModel getProfile(ResultSet result){

        UserModel user = new UserModel();

        try {

            while (result.next()){
                DepartmentModel department = new DepartmentModel();
                PositionModel position = new PositionModel();
                RoleModel role = new RoleModel();
                department.setId(result.getString("department_id"));
                department.setName(result.getString("department_name"));
                department.setShort_name(result.getString("department_short_name"));
                department.setCode(result.getString("department_code"));
                department.setStatus(result.getBoolean("department_status"));
                position.setId(result.getString("position_id"));
                position.setName(result.getString("position_name"));
                position.setCode(result.getString("position_code"));
                position.setStatus(result.getBoolean("position_status"));
                role.setId(result.getString("role_id"));
                role.setName(result.getString("role_name"));
                role.setCode(result.getString("role_code"));
                role.setStatus(result.getBoolean("role_status"));
                user.setId(UUID.fromString(result.getString("id")));
                user.setFull_name(result.getString("full_name"));
                user.setStaff_id(result.getString("staff_id"));
                user.setPassword(result.getString("password"));
                user.setUsername(result.getString("username"));
                user.setDepartment_id(result.getString("department_id"));
                user.setPosition_id(result.getString("position_id"));
                user.setRole_id(result.getString("role_id"));
                user.setStatus(result.getBoolean("status"));
                user.setDepartment_name(result.getString("department_name"));
                user.setPosition_name(result.getString("position_name"));
                user.setRole_name(result.getString("role_name"));
                user.setDepartment(department);
                user.setPosition(position);
                user.setRole(role);
            }

        }catch (Exception e){

        }
        return user;
    }
}
