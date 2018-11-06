package com.tmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tmanagement.model.Admin;
import com.tmanagement.model.User;
import com.tmanagement.service.AdminService;
import com.tmanagement.service.UserService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/secure")
public class LoginController {

    @Autowired
    private UserService userService;
    
    @Autowired
    public AdminService adminServ;
    

    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<List<Admin>> listUser(){
    	System.out.println("Inside getAllAdmins");
        return new ResponseEntity<>(adminServ.getAllAdmins(),HttpStatus.OK);
    }

    //@Secured("ROLE_USER")
    @PreAuthorize("hasRole('USER')")
    ////@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getOne(@PathVariable(value = "id") int id){
        return new ResponseEntity<>(userService.findById(id),HttpStatus.OK);
    }
    
    

   /* @PreAuthorize("hasRole('USER')")
    ////@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/boomm", method = RequestMethod.GET)
    public ResponseEntity<String> getSome(){
        return new ResponseEntity<>("Poooop",HttpStatus.OK);
    }*/

        

    /*@RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody AdminDto user){
        return userService.save(user);
    }
*/


}
