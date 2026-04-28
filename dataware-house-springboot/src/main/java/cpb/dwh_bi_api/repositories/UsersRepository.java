package cpb.dwh_bi_api.repositories;

import cpb.dwh_bi_api.models.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

public class UsersRepository{

    public static String findByUsername(String username){

        return  "SELECT users.*,\n" +
                "departments.id as department_id,\n" +
                "departments.name as department_name,\n" +
                "departments.short_name as department_short_name,\n" +
                "departments.code as department_code,\n" +
                "departments.status as department_status,\n" +
                "departments.created_at as department_created_at,\n" +
                "positions.id as position_id,\n" +
                "positions.name as position_name,\n" +
                "positions.code as position_code,\n" +
                "positions.status as position_status,\n" +
                "positions.created_at as position_created_at,\n" +
                "roles.id as role_id, \n" +
                "roles.name as role_name,\n" +
                "roles.code as role_code,\n" +
                "roles.status as role_status,\n" +
                "roles.created_at as role_created_at\n" +
                "FROM users \n" +
                "INNER JOIN departments ON departments.id = users.department_id \n" +
                "INNER JOIN positions ON positions.id = users.position_id \n" +
                "INNER JOIN roles ON roles.id = users.role_id where users.username='"+username+"'";
    }

    public static String findByUsernameAD(String username){

        return  "SELECT * FROM public.users WHERE username='"+username+"'";
    }

//    public static String findByUsername(String username){
//
//        return "SELECT * FROM public.users where username='"+username+"'";
//    }

    public static String findById(UUID id){
        return  "SELECT users.*,\n" +
                "departments.id as department_id,\n" +
                "departments.name as department_name,\n" +
                "departments.short_name as department_short_name,\n" +
                "departments.code as department_code,\n" +
                "departments.status as department_status,\n" +
                "departments.created_at as department_created_at,\n" +
                "positions.id as position_id,\n" +
                "positions.name as position_name,\n" +
                "positions.code as position_code,\n" +
                "positions.status as position_status,\n" +
                "positions.created_at as position_created_at,\n" +
                "roles.id as role_id, \n" +
                "roles.name as role_name,\n" +
                "roles.code as role_code,\n" +
                "roles.status as role_status,\n" +
                "roles.created_at as role_created_at\n" +
                "FROM users \n" +
                "INNER JOIN departments ON departments.id = users.department_id \n" +
                "INNER JOIN positions ON positions.id = users.position_id \n" +
                "INNER JOIN roles ON roles.id = users.role_id where users.id="+id;

    }

    public static String findAll(){
        return "SELECT users.*,\n" +
                "departments.id as department_id,\n" +
                "departments.name as department_name,\n" +
                "departments.short_name as department_short_name,\n" +
                "departments.code as department_code,\n" +
                "departments.status as department_status,\n" +
                "departments.created_at as department_created_at,\n" +
                "positions.id as position_id,\n" +
                "positions.name as position_name,\n" +
                "positions.code as position_code,\n" +
                "positions.status as position_status,\n" +
                "positions.created_at as position_created_at,\n" +
                "roles.id as role_id, \n" +
                "roles.name as role_name,\n" +
                "roles.code as role_code,\n" +
                "roles.status as role_status,\n" +
                "roles.created_at as role_created_at\n" +
                "FROM users \n" +
                "INNER JOIN departments ON departments.id = users.department_id \n" +
                "INNER JOIN positions ON positions.id = users.position_id \n" +
                "INNER JOIN roles ON roles.id = users.role_id";
    }

    public static String store(UserModel user){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(
                16, new SecureRandom());

        return "INSERT INTO public.users(\n" +
                "\tfull_name, staff_id, username, password, department_id, position_id, role_id,password_expired, created_at)\n" +
                "\tVALUES (" +"'"+
                user.getFull_name()+"','" +
                user.getStaff_id()+"','" +
                user.getUsername()+"','" +
                encoder.encode(user.getPassword())+"','" +
                user.getDepartment_id()+"','" +
                user.getPosition_id()+"','" +
                user.getRole_id()+"','" +
                user.getPassword_expired()+"','" +
                user.getCreated_at()+"'" +
                ")";
    }

    public static String storeAD(UserModel user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(
                16, new SecureRandom());
        return "INSERT INTO public.users(\n" +
                "\tid, full_name, username,password,created_at)\n" +
                "\tVALUES ("+"'"+
                user.getId()+"','"+
                user.getFull_name()+"','" +
                user.getUsername()+"','" +
                encoder.encode(user.getPassword())+"','" +
//                user.getDepartment_id()+"','" +
//                user.getPosition_id()+"','" +
//                user.getRole_id()+"','" +
                user.getCreated_at()+"'" +
                ")";
    }

    public static String update(UserModel user){

        return "UPDATE public.users\n" +
                "\tSET full_name='" +user.getFull_name()+"',\n"+
                "\tstaff_id='" +user.getStaff_id()+"',\n"+
                "\tusername='"+user.getUsername()+"',\n"+
                "\tdepartment_id='" +user.getDepartment_id()+"',\n"+
                "\tposition_id='" +user.getPosition_id()+"',\n"+
                "\trole_id='" +user.getRole_id()+"',\n"+
                "\tupdated_at='"+user.getUpdated_at()+"'\n"+
                "\tWHERE id='"+user.getId()+"'";
    }

    public static String delete(String id){
        return "DELETE FROM public.users WHERE id='"+id+"'";
    }


    public static String resetPassword(UserModel user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(
                16, new SecureRandom());
        return "UPDATE public.users\n" +
                "\tSET password='" +encoder.encode(user.getPassword())+"',\n"+
                "\tpassword_reseted_at='"+user.getPassword_reseted_at()+"'\n"+
                "\tWHERE id='"+user.getId()+"'";
    }


}
