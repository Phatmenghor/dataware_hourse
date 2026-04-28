package cpb.dwh_bi_api.controllers;

import cpb.dwh_bi_api.models.RoleModel;
import cpb.dwh_bi_api.models.StatusResponse;
import cpb.dwh_bi_api.services.RoleService;
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
@RequestMapping("/api/v2/role")
public class RoleController {
    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getAllRole() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            List<RoleModel> response = roleService.getAll();

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
    public ResponseEntity<Map<String, Object>> storeRole(@RequestBody RoleModel role) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            role.setCreated_at(LocalDate.now());
            RoleModel response = roleService.store(role);
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
    public ResponseEntity<Map<String, Object>> udpateRole(@RequestBody RoleModel role) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            role.setCreated_at(LocalDate.now());
            RoleModel response = roleService.update(role);
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
    public ResponseEntity<Map<String, Object>> deleteRole(@RequestBody RoleModel role) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            boolean response = roleService.delete(role);

            if(response) {
                status.setCode(200);
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
