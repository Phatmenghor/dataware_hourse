package cpb.dwh_bi_api.result_set;

import cpb.dwh_bi_api.models.DepartmentModel;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentResultSet {

    public List<DepartmentModel> getAll(ResultSet result){
        List<DepartmentModel> departments = new ArrayList<>();

        try {

            while (result.next()){
                DepartmentModel department = new DepartmentModel();

                department.setId(result.getString("id"));
                department.setName(result.getString("name"));
                department.setShort_name(result.getString("short_name"));
                department.setCode(result.getString("code"));
                department.setStatus(result.getBoolean("status"));
                departments.add(department);
            }

        }catch (Exception e){

        }

        return departments;
    }


    public DepartmentModel getOne(ResultSet result){
        DepartmentModel department = new DepartmentModel();

        try {

            while (result.next()){

                department.setId(result.getString("id"));
                department.setName(result.getString("name"));
                department.setShort_name(result.getString("short_name"));
                department.setCode(result.getString("code"));
                department.setStatus(result.getBoolean("status"));

            }

        }catch (Exception e){

        }

        return department;
    }

}
