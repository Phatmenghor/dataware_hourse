package cpb.dwh_bi_api.controllers;


import cpb.dwh_bi_api.models.CustomerModel;
import cpb.dwh_bi_api.models.StatusResponse;
import cpb.dwh_bi_api.services.CustomerService;
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
@RequestMapping("/api/v2/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getCustomers(@RequestParam int show) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();
        List<CustomerModel> customerList = customerService.getAll(show);

        status.setCode(200);
        status.setMessage("Your Request was successful");
        status.setSuccess(true);
        map.put("data", customerList);
        map.put("status", status);


        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

    }

    @RequestMapping(value = "/get-by-cid", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getCustomersByCID(@RequestParam String cid, @RequestParam String branch) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();
        CustomerModel customerList = customerService.getAllFilter(cid, branch);

        status.setCode(200);
        status.setMessage("Your Request was successful");
        status.setSuccess(true);
        map.put("data", customerList);
        map.put("status", status);


        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

    }


    @RequestMapping(value = "/export-all", method = RequestMethod.GET)
    public void  getCustomersByCID(HttpServletResponse response) throws Exception {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/csv; charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment; filename=customers.csv");

        customerService.writeCustomerToCsv(customerService.getAll(1000), response.getWriter());
    }


}
