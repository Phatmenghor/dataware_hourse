package cpb.dwh_bi_api.models;

import lombok.Data;

@Data
public class JwtUserModel {

    private String id;
    private String username;
    private String password;

}
