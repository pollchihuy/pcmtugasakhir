package coid.bcafinance.pcmtugasakhir.controller;


import coid.bcafinance.pcmtugasakhir.dto.validasi.ValUserDTO;
import coid.bcafinance.pcmtugasakhir.model.User;
import coid.bcafinance.pcmtugasakhir.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public Map<String,Object> getHello(){
        Map<String,Object> m = new HashMap<String, Object>();
        m.put("timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        m.put("message","Sudah Masuk Om !!");
        return m;
    }

    @GetMapping
    public ResponseEntity<Object> getDefault(){
        return null;
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ValUserDTO valUserDTO
    ,HttpServletRequest request){
        return userService.save(userService.convertToEntiy(valUserDTO),request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable(value = "id") Long aLong
            ,@Valid @RequestBody ValUserDTO valUserDTO
    , HttpServletRequest request){
        return userService.update(aLong,userService.convertToEntiy(valUserDTO),request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(){
        return null;
    }

    @GetMapping("/{page}/{sort}/{sort-by}")
    public ResponseEntity<Object> findByParam(
            Integer size,
            String column,
            String value
    ){
        return null;
    }

    /**
     * nama
     * email
     * alamat
     * noHp
     *
     */


}