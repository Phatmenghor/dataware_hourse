package cpb.dwh_bi_api.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserModel {


    private UUID id;
    private String full_name;
    private String staff_id;
    private String username;
    private String password;
    private String department_id;
    private String position_id;
    private String role_id;
    private boolean status;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate created_at;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate updated_at;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate password_reseted_at;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate password_expired;

    private String department_name;
    private String position_name;
    private String role_name;

    private DepartmentModel department;
    private PositionModel position;
    private RoleModel role;

}
