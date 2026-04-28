package cpb.dwh_bi_api.result_set;

import cpb.dwh_bi_api.models.PositionModel;
import cpb.dwh_bi_api.models.RoleModel;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoleResultSet {

    public List<RoleModel> getAll(ResultSet result){
        List<RoleModel> roles = new ArrayList<>();

        try {

            while (result.next()){
                RoleModel role = new RoleModel();

                role.setId(result.getString("id"));
                role.setName(result.getString("name"));
                role.setCode(result.getString("code"));
                role.setStatus(result.getBoolean("status"));

                roles.add(role);
            }

        }catch (Exception e){

        }

        return roles;
    }

    public RoleModel getOne(ResultSet result){

        RoleModel role = new RoleModel();

        try {

            while (result.next()){

                role.setId(result.getString("id"));
                role.setName(result.getString("name"));
                role.setCode(result.getString("code"));
                role.setStatus(result.getBoolean("status"));

            }

        }catch (Exception e){

        }

        return role;
    }
}
