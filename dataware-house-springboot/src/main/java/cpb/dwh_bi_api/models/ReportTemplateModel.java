package cpb.dwh_bi_api.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ReportTemplateModel {

    private int id;
    private String name;
    private String code;
    private int department_id;
    private boolean status;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate created_at;
}
