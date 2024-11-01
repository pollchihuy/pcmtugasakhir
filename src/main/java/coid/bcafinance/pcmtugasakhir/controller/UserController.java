package coid.bcafinance.pcmtugasakhir.controller;


import coid.bcafinance.pcmtugasakhir.dto.validasi.ValUserDTO;
import coid.bcafinance.pcmtugasakhir.model.User;
import coid.bcafinance.pcmtugasakhir.repo.UserRepo;
import coid.bcafinance.pcmtugasakhir.security.BcryptImpl;
import coid.bcafinance.pcmtugasakhir.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    private Map<String,Object> map = new HashMap<>();

    public UserController() {
        initMap();
    }

    public void initMap(){
        map.clear();
        map.put("mail","email");
        map.put("hp","noHp");
        map.put("addr","alamat");
        map.put("nama","namaLengkap");
        map.put("age","umur");
    }

    @GetMapping("/pwd/{id}")
    public Boolean testPassword(@PathVariable(value = "id") Long id,
            @RequestParam(value = "password") String password
            ,HttpServletRequest request){
        Optional<User> optionalUser = userRepo.findById(id);
        User u = optionalUser.get();
        String strHash = u.getPassword();
        System.out.println("STR HASH: "+strHash);
        Boolean b = BcryptImpl.verifyHash(password, strHash);
        return b;
    }
//    @GetMapping("/hello")
//    public Map<String,Object> getHello(){
//        Map<String,Object> m = new HashMap<String, Object>();
//        m.put("timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        m.put("message","Sudah Masuk Om !!");
//        return m;
//    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER-MAN')")
    public ResponseEntity<Object> getDefault(HttpServletRequest request){
        Pageable pageable =  PageRequest.of(0,10,Sort.by("id"));//ASC
        return userService.findAll(pageable,request);
    }

    /** Usman Save */
    @PostMapping
    @PreAuthorize("hasAuthority('USER-MAN')")
    public ResponseEntity<Object> save(@Valid @RequestBody ValUserDTO valUserDTO
    ,HttpServletRequest request){
        return userService.save(userService.convertToEntiy(valUserDTO),request);
    }

    /** Usman Update */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER-MAN')")
    public ResponseEntity<Object> update(
            @PathVariable(value = "id") Long aLong
            ,@Valid @RequestBody ValUserDTO valUserDTO
    , HttpServletRequest request){
        return userService.update(aLong,userService.convertToEntiy(valUserDTO),request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER-MAN')")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, HttpServletRequest request){
        return userService.delete(id,request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id,HttpServletRequest request){
        return userService.findById(id,request);
    }

    // jgn di taruh roll
    @GetMapping("/{page}/{sort}/{sort-by}")
    public ResponseEntity<Object> findByParam(
            @PathVariable(value = "sort") String sort,
            @PathVariable(value = "sort-by") String sortBy,
            @PathVariable(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "col") String column,
            @RequestParam(value = "val") String value,
            HttpServletRequest request
    ){
        Pageable pageable = null;
        sortBy = map.get(sortBy)==null?"id":map.get(sortBy).toString();
        column = map.get(column)==null?"id":map.get(column).toString();
        if("asc".equals(sort)){
            pageable = PageRequest.of(page,size,Sort.by(sortBy));//ASC
        }else{
            pageable = PageRequest.of(page,size,Sort.by(sortBy).descending());//DESC
        }
        return userService.findByParam(pageable,column,value,request);
    }

    @PreAuthorize("hasAuthority('USER-MAN')")
    @PostMapping("/upload-sheet")
    public ResponseEntity<Object> uploadExcel(
            @RequestParam(value = "file") MultipartFile file,
            HttpServletRequest request
    ){
        return userService.uploadDataExcel(file,request);
    }


    @PreAuthorize("hasAuthority('USER-MAN')")
    @GetMapping("/download-sheet")
    public void downloadExcel(
            @RequestParam(value = "col") String column,
            @RequestParam(value = "val") String value,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        column = map.get(column)==null?"id":map.get(column).toString();
        userService.downloadReportExcel(column,value,request,response);
    }

    @PreAuthorize("hasAuthority('USER-MAN')")
    @GetMapping("/download-pdf")
    public void downloadPDF(
            @RequestParam(value = "col") String column,
            @RequestParam(value = "val") String value,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        column = map.get(column)==null?"id":map.get(column).toString();
        userService.generateToPDF(column,value,request,response);
    }
    /**
     * nama
     * email
     * alamat
     * noHp
     *
     */

}