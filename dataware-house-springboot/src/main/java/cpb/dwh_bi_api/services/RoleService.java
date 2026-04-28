package cpb.dwh_bi_api.services;

import cpb.dwh_bi_api.database.PostgrestConnection;
import cpb.dwh_bi_api.models.RoleModel;
import cpb.dwh_bi_api.repositories.RoleRepository;
import cpb.dwh_bi_api.result_set.RoleResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepository;
    private PostgrestConnection postgrestConnection = new PostgrestConnection();

    @Autowired
    private RoleResultSet roleResultSet;

    public List<RoleModel> getAll(){

        String query = roleRepository.findAll();
        ResultSet result = postgrestConnection.selectAll(query);
        return roleResultSet.getAll(result);
    }

    public RoleModel store(RoleModel role) {

        String query = roleRepository.store(role);
        ResultSet result = postgrestConnection.create(query);
        return roleResultSet.getOne(result);

    }

    public RoleModel update(RoleModel role) {

        String query = roleRepository.update(role);
        ResultSet result = postgrestConnection.create(query);
        return roleResultSet.getOne(result);
    }

    public boolean delete(RoleModel role) {
        boolean response = false;

        RoleModel roleModel = new RoleModel();

        String query = roleRepository.findById(role.getId());

        ResultSet result = postgrestConnection.query(query);

        roleModel = roleResultSet.getOne(result);

        if(roleModel.getId() == role.getId()){
            String queryDelete = roleRepository.delete(role.getId());
            postgrestConnection.delete(queryDelete);
            response = true;
        }

        return response;

    }

}
