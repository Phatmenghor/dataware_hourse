package cpb.dwh_bi_api.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import cpb.dwh_bi_api.models.MiniLoanGridModel;
import cpb.dwh_bi_api.models.PastDueGridModel;
import cpb.dwh_bi_api.models.StatusResponse;
import cpb.dwh_bi_api.services.PastDueGridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v2/past-due-grid")
public class PastDueGridController {

    @Autowired
    private PastDueGridService pastDueGridService;

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam int show, @RequestParam String branch) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();
        List<PastDueGridModel> pastDueGridModels = pastDueGridService.getAll(show, branch);

        status.setCode(200);
        status.setMessage("Your Request was successful");
        status.setSuccess(true);
        map.put("data", pastDueGridModels);
        map.put("status", status);

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-by-branch", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getByBranch(@RequestParam String branch, @RequestParam String aaid ,@RequestParam String reportDate,@RequestParam int show) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();
        List<PastDueGridModel> pastDueGridModels = pastDueGridService.getByBranch(branch,aaid,reportDate, show);

        status.setCode(200);
        status.setMessage("Your Request was successful");
        status.setSuccess(true);
        map.put("data", pastDueGridModels);
        map.put("status", status);

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-by-id", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getById(@RequestParam String id) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();
        PastDueGridModel pastDueGridModel = pastDueGridService.getById(id);

        status.setCode(200);
        status.setMessage("Your Request was successful");
        status.setSuccess(true);
        map.put("data", pastDueGridModel);
        map.put("status", status);

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/export-all", method = RequestMethod.GET)
    public void  getExport(HttpServletResponse response, @RequestParam int show, @RequestParam String branch, @RequestParam String reportDate, @RequestParam String currency) throws Exception {
        System.out.println(reportDate);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/csv; charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment; filename=customers.csv");

        pastDueGridService.writeToCsv(pastDueGridService.getExport(show, branch, reportDate, currency), response.getWriter());
    }

}
