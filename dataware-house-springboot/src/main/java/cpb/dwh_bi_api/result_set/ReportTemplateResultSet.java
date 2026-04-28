package cpb.dwh_bi_api.result_set;

import cpb.dwh_bi_api.models.ReportTemplateModel;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReportTemplateResultSet {

    public List<ReportTemplateModel> getAll(ResultSet result){

        List<ReportTemplateModel> reportTemplateModels = new ArrayList<>();

        try{

            while (result.next()){
                ReportTemplateModel reportTemplateModel = new ReportTemplateModel();

                reportTemplateModel.setId(result.getInt("id"));
                reportTemplateModel.setName(result.getString("name"));
                reportTemplateModel.setCode(result.getString("code"));
                reportTemplateModel.setDepartment_id(result.getInt("department_id"));
                reportTemplateModel.setStatus(result.getBoolean("status"));

                reportTemplateModels.add(reportTemplateModel);
            }

        }catch (Exception e){

        }

        return reportTemplateModels;

    }

    public ReportTemplateModel getOne(ResultSet result){

        ReportTemplateModel reportTemplateModel = new ReportTemplateModel();

        try{

            while (result.next()){
                reportTemplateModel.setId(result.getInt("id"));
                reportTemplateModel.setName(result.getString("name"));
                reportTemplateModel.setCode(result.getString("code"));
                reportTemplateModel.setDepartment_id(result.getInt("department_id"));
                reportTemplateModel.setStatus(result.getBoolean("status"));
            }

        }catch (Exception e){

        }

        return reportTemplateModel;

    }
}
