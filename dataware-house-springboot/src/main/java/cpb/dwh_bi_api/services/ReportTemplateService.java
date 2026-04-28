package cpb.dwh_bi_api.services;

import cpb.dwh_bi_api.database.PostgrestConnection;
import cpb.dwh_bi_api.models.ReportTemplateModel;
import cpb.dwh_bi_api.repositories.ReportTemplateRepository;
import cpb.dwh_bi_api.result_set.ReportTemplateResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class ReportTemplateService {

    private ReportTemplateRepository reportTemplateRepository;

    private PostgrestConnection postgrestConnection = new PostgrestConnection();

    @Autowired
    private ReportTemplateResultSet reportTemplateResultSet;


    public List<ReportTemplateModel> getAll(){

        String query = reportTemplateRepository.findAll();

        ResultSet result = postgrestConnection.selectAll(query);

        return reportTemplateResultSet.getAll(result);
    }

    public List<ReportTemplateModel> getByPage(int limit){

        String query = reportTemplateRepository.findAllByPage(limit);

        ResultSet result = postgrestConnection.selectAll(query);

        return reportTemplateResultSet.getAll(result);
    }

    public ReportTemplateModel getById(int id){

        String query = reportTemplateRepository.findById(id);

        ResultSet result = postgrestConnection.selectAll(query);

        return reportTemplateResultSet.getOne(result);
    }

    public ReportTemplateModel store(ReportTemplateModel report){

        String query = reportTemplateRepository.store(report);

        ResultSet result = postgrestConnection.create(query);

        return reportTemplateResultSet.getOne(result);
    }

    public ReportTemplateModel update(ReportTemplateModel report){

        String query = reportTemplateRepository.update(report);

        ResultSet result = postgrestConnection.create(query);

        return reportTemplateResultSet.getOne(result);
    }

    public boolean delete(ReportTemplateModel report){

        String query = reportTemplateRepository.delete(report.getId());

        postgrestConnection.query(query);

        return true;
    }


}
