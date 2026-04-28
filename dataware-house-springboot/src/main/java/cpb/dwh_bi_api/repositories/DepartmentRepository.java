package cpb.dwh_bi_api.repositories;

import cpb.dwh_bi_api.models.DepartmentModel;

public class DepartmentRepository {
    public static String findAll(){
        return "select * from departments";
    }
    public static String findAllByPage(int limit){
        if(limit ==0){
            return "SELECT * FROM departments";
        }
        return "SELECT * FROM departments LIMIT "+limit;
    }

    public static String findById(String id){
        return "select * from departments where id ='"+id+"'";
    }

    public static String store(DepartmentModel department){

        return "INSERT INTO public.departments(\n" +
                "\tname, short_name, code, created_at)\n" +
                "\tVALUES ('"+department.getName()+"', '"+department.getShort_name()+"', '"+department.getCode()+"', '"+department.getCreated_at()+"')";
    }

    public static String update(DepartmentModel department){

        return "UPDATE public.departments\n" +
                "\tSET name='"+department.getName()+"', short_name='"+department.getShort_name()+"', code='"+department.getCode()+"', created_at='"+department.getCreated_at()+"'\n" +
                "\tWHERE id='"+department.getId()+"'";

    }

    public static String delete(String id){

        return "DELETE FROM public.departments WHERE id="+id;

    }
}
