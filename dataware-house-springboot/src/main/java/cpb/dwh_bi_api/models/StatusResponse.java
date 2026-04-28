package cpb.dwh_bi_api.models;

import lombok.Data;

@Data
public class StatusResponse {
    public int code;
    public boolean success;
    public String message;
}
