package cpb.dwh_bi_api.controllers;

import cpb.dwh_bi_api.models.DepartmentModel;
import cpb.dwh_bi_api.models.StatusResponse;
import cpb.dwh_bi_api.services.DepartmentService;
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
@RequestMapping("/api/v2/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getAll() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            List<DepartmentModel> response = departmentService.getAll();

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
        System.out.println(show);
        try {

            List<DepartmentModel> response = departmentService.getAllByPage(show);

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
    public ResponseEntity<Map<String, Object>> storeDepartment(@RequestBody DepartmentModel department) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            department.setCreated_at(LocalDate.now());
            DepartmentModel response = departmentService.store(department);
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
    public ResponseEntity<Map<String, Object>> updateDepartment(@RequestBody DepartmentModel department) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            department.setCreated_at(LocalDate.now());
            DepartmentModel response = departmentService.update(department);

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
    public ResponseEntity<Map<String, Object>> deleteDepartment(@RequestBody DepartmentModel department) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            boolean response =  departmentService.delete(department);

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
