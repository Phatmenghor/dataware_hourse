package cpb.dwh_bi_api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cpb.dwh_bi_api.models.StatusResponse;
import cpb.dwh_bi_api.models.UserModel;
import cpb.dwh_bi_api.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v2/users")
public class UsersController {

    @Autowired
    private UsersService userService;

    @RequestMapping(value = "/get-profile", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            UserModel responseUser = userService.getProfile(authorization);

            status.setCode(200);
            status.setMessage("Your Request was successful");
            status.setSuccess(true);
            map.put("data", responseUser);
            map.put("status", status);

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

    @RequestMapping(value = "/get-all", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> allUsers() throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            List<UserModel> responseUser = userService.getAll();

            status.setCode(200);
            status.setMessage("Your Request was successful");
            status.setSuccess(true);
            map.put("data", responseUser);
            map.put("status", status);

        } catch (Exception e) {
            status.setCode(204);
            status.setMessage("Your Content Unexceptionable!");
            status.setSuccess(false);
            map.put("data", null);
            map.put("status", status);
            e.printStackTrace();
        }

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/find-by-id", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> findById(@RequestBody UserModel user) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            UserModel responseUser = userService.findById(user.getId());

            status.setCode(200);
            status.setMessage("Your Request was successful");
            status.setSuccess(true);
            map.put("data", responseUser);
            map.put("status", status);

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

//    @RequestMapping(value = "/find-by-username", method = RequestMethod.POST)
//    public ResponseEntity<Map<String, Object>> findByUsername(@RequestBody UserModel user) throws Exception {
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        StatusResponse status = new StatusResponse();
//
//        try {
//
//            UsersEntity responseUser = userService.findByUsername(user.getUsername());
//
//            status.setCode(200);
//            status.setMessage("Your Request was successful");
//            status.setSuccess(true);
//            map.put("data", responseUser);
//            map.put("status", status);
//
//        } catch (Exception e) {
//            status.setCode(204);
//            status.setMessage("Your Content Unexceptable!");
//            status.setSuccess(false);
//            map.put("data", null);
//            map.put("status", status);
//            e.printStackTrace();
//        }
//
//        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//    }

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> storeUser(@RequestBody UserModel user) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            user.setCreated_at(LocalDate.now());
            user.setPassword_expired(LocalDate.now());
            user.setPassword("123cpbank!");
            UserModel responseUser = userService.store(user);

            if(responseUser !=null) {

                status.setCode(200);
                status.setMessage("Your Request was successful");
                status.setSuccess(true);
                map.put("data", responseUser);
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
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody UserModel user) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {
            user.setUpdated_at(LocalDate.now());
            UserModel responseUser = userService.update(user);

            if(responseUser !=null) {

                status.setCode(200);
                status.setMessage("Your Request was successful");
                status.setSuccess(true);
                map.put("data", responseUser);
                map.put("status", status);
            }

        } catch (Exception e) {
            status.setCode(204);
            status.setMessage("Your Content Unexceptionable!");
            status.setSuccess(false);
            map.put("data", null);
            map.put("status", status);
            e.printStackTrace();
        }

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestBody UserModel user) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();

        try {

            boolean response = userService.delete(user);

            if(response) {
                status.setCode(200);
                status.setMessage("Your Request was successful");
                status.setSuccess(true);
                map.put("data", "User was deleted from system. thank");
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

    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody UserModel user) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        StatusResponse status = new StatusResponse();


        try {

            user.setPassword_reseted_at(LocalDate.now());

            UserModel responseUser = userService.resetPassword(user);

            if(responseUser !=null) {

                status.setCode(200);
                status.setMessage("Your Request was successful");
                status.setSuccess(true);
                map.put("data", responseUser);
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
