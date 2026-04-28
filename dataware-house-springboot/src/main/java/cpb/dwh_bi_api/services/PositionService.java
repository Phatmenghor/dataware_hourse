package cpb.dwh_bi_api.services;

import cpb.dwh_bi_api.database.PostgrestConnection;

import cpb.dwh_bi_api.models.PositionModel;
import cpb.dwh_bi_api.repositories.PositionRepository;
import cpb.dwh_bi_api.result_set.PositionResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.util.List;

@Service
public class PositionService {


    private PositionRepository positionRepository;
    private PostgrestConnection postgrestConnection = new PostgrestConnection();

    @Autowired
    private PositionResultSet positionResultSet;

    public List<PositionModel> getAll(){

        String query= positionRepository.findAll();

        ResultSet result = postgrestConnection.selectAll(query);

        return positionResultSet.getAll(result);
    }

    public PositionModel store(PositionModel position) {
        String query= positionRepository.store(position);
        ResultSet result = postgrestConnection.selectAll(query);
        return positionResultSet.getOne(result);
    }

    public PositionModel update(PositionModel position) {

        String query = positionRepository.update(position);

        ResultSet result = postgrestConnection.create(query);

        return positionResultSet.getOne(result);
    }

    public boolean delete(PositionModel position) {

        boolean response = false;

        PositionModel positionModel = new PositionModel();

        String query = positionRepository.findById(position.getId());

        ResultSet result = postgrestConnection.query(query);

        positionModel = positionResultSet.getOne(result);

        if(positionModel.getId() == position.getId()){
            String queryDelete = positionRepository.delete(position.getId());
            postgrestConnection.delete(queryDelete);
            response = true;
        }

        return response;
    }
}
