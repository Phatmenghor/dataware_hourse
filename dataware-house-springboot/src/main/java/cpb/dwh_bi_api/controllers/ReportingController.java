package cpb.dwh_bi_api.controllers;

import cpb.dwh_bi_api.database.OracleConnectionJDBC;
import cpb.dwh_bi_api.dto.ReportRequest;
import cpb.dwh_bi_api.models.StatusResponse;
import cpb.dwh_bi_api.services.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v2/reporting")
public class ReportingController {

    @Autowired
    private ReportingService reportingService;

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getCustomers(@RequestParam String query) throws Exception {
        Map<String, Object> map = new HashMap<>();
        StatusResponse status = new StatusResponse();

        if (query == null || query.isEmpty()) {
            status.setCode(400);
            status.setMessage("Query parameter is required.");
            status.setSuccess(false);
            map.put("status", status);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        List<?> reportLis = reportingService.getAll(query);
        status.setCode(200);
        status.setMessage("Your Request was successful");
        status.setSuccess(true);

        map.put("data", reportLis);
        map.put("status", status);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/to-excel")
    public ResponseEntity<byte[]> generateReport(@RequestBody ReportRequest request) {
        try {
            byte[] reportBytes = reportingService.generateReport(request);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + request.getReportName() + ".xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(reportBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/total-pages", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Integer>> getTotalPages(@RequestParam String query) {
        ReportRequest request = new ReportRequest();
        request.setQuery(query); // Set the query parameter
        try {
            int totalPages = reportingService.calculateTotalRecords(query);
            Map<String, Integer> response = new HashMap<>();
            response.put("totalPages", totalPages);
            return ResponseEntity.ok(response);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
