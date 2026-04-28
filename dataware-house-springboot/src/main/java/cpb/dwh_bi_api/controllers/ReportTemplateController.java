package cpb.dwh_bi_api.controllers;

import cpb.dwh_bi_api.models.DepartmentModel;
import cpb.dwh_bi_api.models.ReportTemplateModel;
import cpb.dwh_bi_api.models.StatusResponse;
import cpb.dwh_bi_api.services.ReportTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v2/report-template")
public class ReportTemplateController {

    @Autowired
    private ReportTemplateService reportTemplateService;


    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getAll() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();
        try {

            List<ReportTemplateModel> response = reportTemplateService.getAll();

            if(response !=null) {
                status.setCode(200);
                status.setMessage("Your Request was successful");
                status.setSuccess(true);
                map.put("data", response);
                map.put("status", status);
            }

        } catch (Exception e) {
            status.setCode(204);
            status.setMessage("Your Content Unexceptable!");
            status.setSuccess(false);
            map.put("data", null);
            map.put("status", status);
            e.printStackTrace();
        }


        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

    }
    @RequestMapping(value = "/get-by-page", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getByPage(@RequestParam int show) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            List<ReportTemplateModel> response = reportTemplateService.getByPage(show);

            if(response !=null) {
                status.setCode(200);
                status.setMessage("Your Request was successful");
                status.setSuccess(true);
                map.put("data", response);
                map.put("status", status);
            }

        } catch (Exception e) {
            status.setCode(204);
            status.setMessage("Your Content Unexceptable!");
            status.setSuccess(false);
            map.put("data", null);
            map.put("status", status);
            e.printStackTrace();
        }

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> storeDepartment(@RequestBody ReportTemplateModel report) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            report.setCreated_at(LocalDate.now());
            ReportTemplateModel response = reportTemplateService.store(report);
            if(response !=null) {
                status.setCode(200);
                status.setMessage("Your Request was successful");
                status.setSuccess(true);
                map.put("data", response);
                map.put("status", status);
            }

        } catch (Exception e) {
            status.setCode(204);
            status.setMessage("Your Content Unexceptable!");
            status.setSuccess(false);
            map.put("data", null);
            map.put("status", status);
            e.printStackTrace();
        }

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateDepartment(@RequestBody ReportTemplateModel report) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            report.setCreated_at(LocalDate.now());
            ReportTemplateModel response = reportTemplateService.update(report);

            if(response !=null) {
                status.setCode(200);
                status.setMessage("Your Request was successful");
                status.setSuccess(true);
                map.put("data", response);
                map.put("status", status);
            }

        } catch (Exception e) {
            status.setCode(204);
            status.setMessage("Your Content Unexceptable!");
            status.setSuccess(false);
            map.put("data", null);
            map.put("status", status);
            e.printStackTrace();
        }

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> deleteDepartment(@RequestBody ReportTemplateModel report) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            boolean response =  reportTemplateService.delete(report);

            if(response) {
                status.setCode(200);
                status.setMessage("Your Request was successful");
                status.setSuccess(true);
                map.put("data", "Department was deleted from system. thank");
                map.put("status", status);
            }

        } catch (Exception e) {
            status.setCode(204);
            status.setMessage("Your Content Unexceptable!");
            status.setSuccess(false);
            map.put("data", null);
            map.put("status", status);
            e.printStackTrace();
        }

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

    }
}
