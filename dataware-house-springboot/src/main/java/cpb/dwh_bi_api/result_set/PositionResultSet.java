package cpb.dwh_bi_api.result_set;

import cpb.dwh_bi_api.models.PositionModel;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class PositionResultSet {

    public List<PositionModel> getAll(ResultSet result){
        List<PositionModel> positions = new ArrayList<>();

        try {

            while (result.next()){
                PositionModel position = new PositionModel();

                position.setId(result.getString("id"));
                position.setName(result.getString("name"));
                position.setCode(result.getString("code"));
                position.setStatus(result.getBoolean("status"));

                positions.add(position);
            }

        }catch (Exception e){

        }

        return positions;
    }

    public PositionModel getOne(ResultSet result){

        PositionModel position = new PositionModel();

        try {

            while (result.next()){

                position.setId(result.getString("id"));
                position.setName(result.getString("name"));
                position.setCode(result.getString("code"));
                position.setStatus(result.getBoolean("status"));

            }

        }catch (Exception e){

        }

        return position;
    }
}
