package cpb.dwh_bi_api.repositories;

import cpb.dwh_bi_api.models.PositionModel;
import cpb.dwh_bi_api.models.RoleModel;

public class RoleRepository {

    public static String findAll(){
        return "select * from roles order by created_at desc";
    }

    public static String findById(String id){
        return "select * from roles where id ='"+id+"'";
    }

    public static String store(RoleModel role){
        return "INSERT INTO public.roles(\n" +
                "\tname, code, created_at)\n" +
                "\tVALUES ('"+role.getName()+"','"+role.getCode()+"', '"+role.getCreated_at()+"')";
    }

    public static String update(RoleModel role){

        return "UPDATE public.roles\n" +
                "\tSET name='"+role.getName()+"', code='"+role.getCode()+"', created_at='"+role.getCreated_at()+"'\n" +
                "\tWHERE id='"+role.getId()+"'";

    }

    public static String delete(String id){
        return "DELETE FROM public.roles WHERE id="+id;
    }
}
