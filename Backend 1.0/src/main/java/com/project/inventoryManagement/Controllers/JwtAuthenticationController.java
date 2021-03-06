package com.project.inventoryManagement.Controllers;

import com.project.inventoryManagement.Models.EmployeeMainModel;
import com.project.inventoryManagement.Models.JwtRequest;
import com.project.inventoryManagement.Models.JwtResponse;
import com.project.inventoryManagement.Repositories.EmployeeMainRepository;
import com.project.inventoryManagement.Service.AESForCrossOrigin;
import com.project.inventoryManagement.Service.JwtUserDetailsService;
import com.project.inventoryManagement.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private EmployeeMainRepository employeeMainRepository;

    @Autowired
    private AESForCrossOrigin aesForCrossOrigin;

   // login request comes here
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {




        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        EmployeeMainModel thisUser = employeeMainRepository.findByEmail(authenticationRequest.getUsername());

        final String status = thisUser.getStatus();     // added newly
        final String email = thisUser.getEmail();
        final String nic = thisUser.getNic();
        final String firstname = thisUser.getFirstname();
        final String lastname = thisUser.getLastname();
        final String img = thisUser.getImg();
        final String contactNo = thisUser.getContactNo();
        final boolean unblocked = thisUser.isUnblocked();
        // return ResponseEntity.ok(new JwtResponse(token));    // old
        return ResponseEntity.ok(new JwtResponse(token, status, email, nic, firstname, lastname, img, contactNo, unblocked));   // added newly
    }

//////////////////////////////

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody EmployeeMainModel user) throws Exception {

        return ResponseEntity.ok(userDetailsService.save(user));
    }
/////////////////////////////

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

