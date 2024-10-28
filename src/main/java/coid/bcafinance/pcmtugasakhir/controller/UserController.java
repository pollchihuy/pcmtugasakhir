package coid.bcafinance.pcmtugasakhir.controller;


import coid.bcafinance.pcmtugasakhir.dto.validasi.ValUserDTO;
import coid.bcafinance.pcmtugasakhir.repo.UserRepo;
import coid.bcafinance.pcmtugasakhir.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

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
//    @GetMapping("/hello")
//    public Map<String,Object> getHello(){
//        Map<String,Object> m = new HashMap<String, Object>();
//        m.put("timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        m.put("message","Sudah Masuk Om !!");
//        return m;
//    }

    @GetMapping
    public ResponseEntity<Object> getDefault(){
        return null;//
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
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, HttpServletRequest request){
        return userService.delete(id,request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id,HttpServletRequest request){
        return userService.findById(id,request);
    }

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
        System.out.println("VALUE : "+map.get(column));
        sortBy = map.get(sortBy)==null?"id":map.get(sortBy).toString();
        column = map.get(column)==null?"id":map.get(column).toString();
        if("asc".equals(sort)){
            pageable = PageRequest.of(page,size,Sort.by(sortBy));//ASC
        }else{
            pageable = PageRequest.of(page,size,Sort.by(sortBy).descending());//DESC
        }
//        Pageable pageable = PageRequest.of(page,size,Sort.by(column).descending());//ASC
//                sort.equals("desc")? Sort.by(objSortBy.toString()).descending():Sort.by(sortBy));
//        Pageable pageable = PageRequest.of(,
//                sort.equals("desc")? Sort.by(objSortBy.toString()).descending():Sort.by(sortBy));
        return userService.findByParam(pageable,column,value,request);
    }

    @PostMapping("/upload-sheet")
    public ResponseEntity<Object> uploadExcel(
            @RequestParam(value = "file") MultipartFile file,
            HttpServletRequest request
    ){
        return userService.uploadDataExcel(file,request);
    }


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