package cpb.dwh_bi_api.controllers;


import cpb.dwh_bi_api.models.MiniLoanGridModel;
import cpb.dwh_bi_api.models.StatusResponse;
import cpb.dwh_bi_api.services.MiniLoanGridService;
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
@RequestMapping("/api/v2/mini-loan-grid")
public class MiniLoanGridController {

    @Autowired
    private MiniLoanGridService miniLoanGridService;

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getCustomers(@RequestParam int show, @RequestParam String branch) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();
        List<MiniLoanGridModel> miniLoanGridLists = miniLoanGridService.getAll(show, branch);

        status.setCode(200);
        status.setMessage("Your Request was successful");
        status.setSuccess(true);
        map.put("data", miniLoanGridLists);
        map.put("status", status);

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/all-by-branch", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getByBranch(@RequestParam String branch, @RequestParam String cid,@RequestParam String reportDate,@RequestParam int show, @RequestParam String coid) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();
        List<MiniLoanGridModel> miniLoanGridLists = miniLoanGridService.getByBranch(branch, cid,reportDate,show, coid);

        status.setCode(200);
        status.setMessage("Your Request was successful");
        status.setSuccess(true);
        map.put("data", miniLoanGridLists);
        map.put("status", status);

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/gey-by-aaid", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getByAAID(@RequestParam String id) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();
        MiniLoanGridModel miniLoanGrid = miniLoanGridService.getByAAID(id);

        status.setCode(200);
        status.setMessage("Your Request was successful");
        status.setSuccess(true);
        map.put("data", miniLoanGrid);
        map.put("status", status);

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/export-all", method = RequestMethod.GET)
    public void  getCustomersByCID(HttpServletResponse response, @RequestParam int show, @RequestParam String branch,@RequestParam String reportDate, @RequestParam String currency) throws Exception {
        System.out.println(reportDate);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/csv; charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment; filename=customers.csv");

        miniLoanGridService.writeToCsv(miniLoanGridService.getExport(show, branch, reportDate, currency), response.getWriter());
    }


}
