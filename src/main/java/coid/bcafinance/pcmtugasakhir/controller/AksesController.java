package coid.bcafinance.pcmtugasakhir.controller;


import coid.bcafinance.pcmtugasakhir.dto.validasi.ValAksesDTO;
import coid.bcafinance.pcmtugasakhir.dto.validasi.ValUserDTO;
import coid.bcafinance.pcmtugasakhir.service.AksesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("akses")
public class AksesController {

    @Autowired
    private AksesService aksesService;

    
//    @Value("${server.port}")
//    private int port;
    private Map<String,Object> map = new HashMap<>();

    public AksesController() {
        initMap();
    }

    public void initMap(){
        map.clear();
        map.put("nama","namaLengkap");
    }

    @GetMapping
    public ResponseEntity<Object> getDefault(HttpServletRequest request){
        Pageable pageable =  PageRequest.of(0,10,Sort.by("id"));//ASC
        return aksesService.findAll(pageable,request);
    }

    /** Usman Save */
    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ValAksesDTO valAksesDTO
    ,HttpServletRequest request){
        return aksesService.save(aksesService.convertToEntiy(valAksesDTO),request);
    }

    /** Usman Update */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable(value = "id") Long aLong
            ,@Valid @RequestBody ValAksesDTO valAksesDTO
    , HttpServletRequest request){
        return aksesService.update(aLong, aksesService.convertToEntiy(valAksesDTO),request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, HttpServletRequest request){
        return aksesService.delete(id,request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id,HttpServletRequest request){
        return aksesService.findById(id,request);
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
        System.out.println("VALUE : "+map.get(column));
        sortBy = map.get(sortBy)==null?"id":map.get(sortBy).toString();
        column = map.get(column)==null?"id":map.get(column).toString();
        if("asc".equals(sort)){
            pageable = PageRequest.of(page,size,Sort.by(sortBy));//ASC
        }else{
            pageable = PageRequest.of(page,size,Sort.by(sortBy).descending());//DESC
        }
        return aksesService.findByParam(pageable,column,value,request);
    }

    @PostMapping("/upload-sheet")
    public ResponseEntity<Object> uploadExcel(
            @RequestParam(value = "file") MultipartFile file,
            HttpServletRequest request
    ){
        return aksesService.uploadDataExcel(file,request);
    }

    @GetMapping("/download-sheet")
    public void downloadExcel(
            @RequestParam(value = "col") String column,
            @RequestParam(value = "val") String value,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        column = map.get(column)==null?"id":map.get(column).toString();
        aksesService.downloadReportExcel(column,value,request,response);
    }

    @GetMapping("/download-pdf")
    public void downloadPDF(
            @RequestParam(value = "col") String column,
            @RequestParam(value = "val") String value,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        column = map.get(column)==null?"id":map.get(column).toString();
        aksesService.generateToPDF(column,value,request,response);
    }

    /**
     * nama
     * email
     * alamat
     * noHp
     *
     */


}