package cpb.dwh_bi_api.controllers;

import cpb.dwh_bi_api.models.JwtRequest;
import cpb.dwh_bi_api.models.JwtResponse;
import cpb.dwh_bi_api.models.StatusResponse;
import cpb.dwh_bi_api.models.UserModel;
import cpb.dwh_bi_api.services.JwtUserDetailsService;
import cpb.dwh_bi_api.services.UsersService;
import cpb.dwh_bi_api.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.LdapContext;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;

@RestController
@CrossOrigin
@RequestMapping("/api/v2")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> userStatus(@RequestBody JwtRequest authenticationRequest) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        JwtResponse jwt = new JwtResponse();

        StatusResponse status = new StatusResponse();
        try {

//            Boolean isAuth = userDetailsService.getUserAD(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            if(true){

                authenticate(authenticationRequest.getUsername(), "123cpbank!");

                final UserDetails userDetails = userDetailsService
                        .loadUserByUsername(authenticationRequest.getUsername());
                final String token = jwtTokenUtil.generateToken(userDetails);
                final String username = jwtTokenUtil.getUsernameFromToken(token);
                final String id = String.valueOf(usersService.findByUsernameAD(username).getId());
                final String department_id = String.valueOf(usersService.findByUsernameAD(username).getDepartment_id());
                jwt.setJwttoken(token);
                jwt.setId(id);
                jwt.setDepartment_id(department_id);

                if (jwt.getJwttoken() != "") {
                    status.setCode(200);
                    status.setSuccess(true);
                    status.setMessage("Your Request was successful.");
                    map.put("data",jwt);
                    map.put("status",status);
                }

            }else{
                status.setCode(401);
                status.setMessage("Unexceptable");
                status.setSuccess(false);
                map.put("data", null);
                map.put("status", status);
            }


        } catch (Exception e) {
            status.setCode(401);
            status.setMessage("Unexceptable");
            status.setSuccess(false);
            map.put("data", null);
            map.put("status", status);
            e.printStackTrace();
        }
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);

        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
