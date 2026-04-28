package cpb.dwh_bi_api.repositories;

import cpb.dwh_bi_api.models.DepartmentModel;
import cpb.dwh_bi_api.models.ReportTemplateModel;

public class ReportTemplateRepository {

    public static String findAll(){
        return "select * from reports";
    }
    public static String findAllByPage(int limit){
        if(limit ==0){
            return "SELECT * FROM reports";
        }
        return "SELECT * FROM reports LIMIT "+limit;
    }

    public static String findById(int id){
        return "select * from reports where id ='"+id+"'";
    }

    public static String store(ReportTemplateModel report){

        return "INSERT INTO public.reports(\n" +
                "\tname, code, department_id,status,created_at)\n" +
                "\tVALUES ('"+report.getName()+"', '"+report.getCode()+"', '"+report.getDepartment_id()+"','"+true+"','"+report.getCreated_at()+"')";
    }

    public static String update(ReportTemplateModel report){

        return "UPDATE public.reports\n" +
                "\tSET name='"+report.getName()+"', department_id='"+report.getDepartment_id()+"', code='"+report.getCode()+"', created_at='"+report.getCreated_at()+"'\n" +
                "\tWHERE id='"+report.getId()+"'";

    }

    public static String delete(int id){

        return "DELETE FROM public.reports WHERE id="+id;

    }

}
