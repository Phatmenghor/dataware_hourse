package cpb.dwh_bi_api.models;

import lombok.Data;

import java.io.Serializable;
@Data
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    protected String jwttoken;
    protected String id;
    protected String department_id;
}
