package cpb.dwh_bi_api.services;

import cpb.dwh_bi_api.database.PostgrestConnection;
import cpb.dwh_bi_api.models.DepartmentModel;
import cpb.dwh_bi_api.repositories.DepartmentRepository;
import cpb.dwh_bi_api.result_set.DepartmentResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    private PostgrestConnection postgrestConnection = new PostgrestConnection();

    @Autowired
    private DepartmentResultSet departmentResultSet;

    public List<DepartmentModel> getAll() {

        String query = departmentRepository.findAll();

        ResultSet result = postgrestConnection.selectAll(query);

        return departmentResultSet.getAll(result);

    }

    public List<DepartmentModel> getAllByPage(int show) {

        String query = departmentRepository.findAllByPage(show);

        ResultSet result = postgrestConnection.selectAll(query);

        return departmentResultSet.getAll(result);

    }

    public DepartmentModel findById(String id){

        String query = departmentRepository.findById(id);

        ResultSet result = postgrestConnection.selectAll(query);

        return departmentResultSet.getOne(result);
    }

    public DepartmentModel store(DepartmentModel depart) {

        String query = departmentRepository.store(depart);

        ResultSet result = postgrestConnection.create(query);

        return departmentResultSet.getOne(result);
    }

    public DepartmentModel update(DepartmentModel depart) {

        String query = departmentRepository.update(depart);

        ResultSet result = postgrestConnection.create(query);

        return departmentResultSet.getOne(result);

    }

    public boolean delete(DepartmentModel depart) {
        boolean response = false;

        DepartmentModel department = new DepartmentModel();

        String query = departmentRepository.findById(depart.getId());

        ResultSet result = postgrestConnection.query(query);

        department = departmentResultSet.getOne(result);

        if(department.getId() == depart.getId()){
            String queryDelete = departmentRepository.delete(depart.getId());
            postgrestConnection.delete(queryDelete);
            response = true;
        }

        return response;
    }


}
