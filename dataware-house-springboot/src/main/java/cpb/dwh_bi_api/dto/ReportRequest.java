package cpb.dwh_bi_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequest {
    private String reportName;
    private String reportDate;
    private String branch;
    private List<Map<String, String>> columns;
    private String query;
}
