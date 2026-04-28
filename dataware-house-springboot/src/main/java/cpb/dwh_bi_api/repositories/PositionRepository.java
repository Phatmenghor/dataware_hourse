package cpb.dwh_bi_api.repositories;


import cpb.dwh_bi_api.models.DepartmentModel;
import cpb.dwh_bi_api.models.PositionModel;

public class PositionRepository{
    public static String findAll(){
        return "select * from positions order by created_at desc";
    }

    public static String findById(String id){
        return "select * from positions where id ='"+id+"'";
    }

    public static String store(PositionModel position){
        return "INSERT INTO public.positions(\n" +
                "\tname, code, created_at)\n" +
                "\tVALUES ('"+position.getName()+"','"+position.getCode()+"', '"+position.getCreated_at()+"')";
    }

    public static String update(PositionModel position){

        return "UPDATE public.positions\n" +
                "\tSET name='"+position.getName()+"', code='"+position.getCode()+"', created_at='"+position.getCreated_at()+"'\n" +
                "\tWHERE id='"+position.getId()+"'";

    }

    public static String delete(String id){
        return "DELETE FROM public.positions WHERE id="+id;
    }

}
