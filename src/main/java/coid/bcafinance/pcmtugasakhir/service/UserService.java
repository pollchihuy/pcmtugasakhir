package coid.bcafinance.pcmtugasakhir.service;


import coid.bcafinance.pcmtugasakhir.core.IFile;
import coid.bcafinance.pcmtugasakhir.core.IService;
import coid.bcafinance.pcmtugasakhir.dto.response.RespUserDTO;
import coid.bcafinance.pcmtugasakhir.dto.validasi.ValLoginDTO;
import coid.bcafinance.pcmtugasakhir.dto.validasi.ValUserDTO;
import coid.bcafinance.pcmtugasakhir.model.User;
import coid.bcafinance.pcmtugasakhir.repo.CobaCobaRepo;
import coid.bcafinance.pcmtugasakhir.repo.UserRepo;
import coid.bcafinance.pcmtugasakhir.util.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Platform Code : AUT
 * Modul Code : 004
 *
 */
@Service
@Transactional
public class UserService implements IService<User>, IFile<User> {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CobaCobaRepo cobaCobaRepo;

    private TransformPagination transformPagination = new TransformPagination();
    private ModelMapper modelMapper = new ModelMapper();
    private StringBuilder sBuild = new StringBuilder();

    @Autowired
    private PdfGenerator pdfGenerator;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Override
    public ResponseEntity<Object> save(User user,HttpServletRequest request) {
        try{
            userRepo.save(user);
        }catch (Exception e) {
            return GlobalFunction.dataGagalDisimpan("FEAUT004001",request);
        }
        return GlobalFunction.dataBerhasilDisimpan(request);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, User user, HttpServletRequest request) {
        try{
            Optional<User> optionalUser = userRepo.findById(id);
            if(!optionalUser.isPresent()) {
                return GlobalFunction.dataTidakDitemukan(request);
            }
            User userNext = optionalUser.get();
            userNext.setAlamat(user.getAlamat());
            userNext.setEmail(user.getEmail());
            userNext.setPassword(user.getPassword());
            userNext.setNamaLengkap(user.getNamaLengkap());
            userNext.setNoHp(user.getNoHp());
            userNext.setUsername(user.getUsername());
            userNext.setAkses(user.getAkses());
            userNext.setModifiedBy(1L);
        }catch (Exception e) {
            return GlobalFunction.dataGagalDisimpan("FEAUT004011",request);//011-020
        }
        return GlobalFunction.dataBerhasilDiubah(request);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            Optional<User> optionalUser = userRepo.findById(id);
            if(!optionalUser.isPresent()) {
                return GlobalFunction.dataTidakDitemukan(request);
            }
            userRepo.deleteById(id);
        }catch (Exception e){
            return GlobalFunction.dataGagalDihapus("FEAUT004021",request);//021-030
        }
        return GlobalFunction.dataBerhasilDihapus(request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<User> optionalUser = userRepo.findById(id);
        if(!optionalUser.isPresent()) {
            return GlobalFunction.dataTidakDitemukan(request);
        }
        User user = optionalUser.get();

        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(user, RespUserDTO.class));
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        Page<User> page = null;
        List<User> list = null;
        switch (columnName){
            case "alamat":page=userRepo.findByAlamatContainingIgnoreCase(pageable,value);break;
            case "email":page=userRepo.findByEmailContainingIgnoreCase(pageable,value);break;
            case "namaLengkap":page=userRepo.findByNamaLengkapContainingIgnoreCase(pageable,value);break;
            case "noHp":page=userRepo.findByNoHpContainingIgnoreCase(pageable,value);break;
            case "umur":page=userRepo.cariUmur(pageable,value);break;
//            case "umur":page=userRepo.cariUmur(pageable,value);break;
            default:page=userRepo.findAll(pageable);
        }
        list = page.getContent();
        if (list.isEmpty()){
            return GlobalFunction.dataTidakDitemukan(request);
        }
        return transformPagination.transformObject(
                new HashMap<>(),
                convertToListRespUserDTO(list),
                page,
                columnName,value
        );
    }

    @Transactional
    public ResponseEntity<Object> uploadDataExcel(MultipartFile multipartFile, HttpServletRequest request) {
        String message = "";
        if (!ExcelReader.hasWorkBookFormat(multipartFile)) {
            return ResponseEntity.status(400).body("Harus Excel");
        }

        try {
            List lt  = new ExcelReader(multipartFile.getInputStream(),"Sheet1").getDataMap();
            if(lt.isEmpty()){
//                return GlobalFunction.dataWorkBookKosong("FV002002062",request);
            }
            //KARENA DATA LIST MAP<String,String> maka harus di convert menjadi Entity
            userRepo.saveAll(convertListWorkBookToListEntity(lt,1L));
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            return GlobalFunction.tidakDapatDiproses("FE002002061",request);
        }
        return GlobalFunction.dataBerhasilDisimpan(request);
    }

    public List<User> convertListWorkBookToListEntity(List<Map<String, String>> workBookData, Long userId){
        List<User> list = new ArrayList<>();
        for (int i = 0; i < workBookData.size(); i++) {
            Map<String, String> map = workBookData.get(i);
            User user = new User();
            user.setNamaLengkap(map.get("nama_lengkap"));
            user.setNoHp(map.get("no_hp"));
            user.setUsername(map.get("username"));
            user.setAlamat(map.get("alamat"));
//            user.setUmur(Integer.parseInt(map.get("umur")));
            user.setEmail(map.get("email"));
            list.add(user);
        }
        return list;
    }

    public void downloadReportExcel(String column, String value, HttpServletRequest request, HttpServletResponse response) {
        List<User> userList = null;
        switch (column){
            case "alamat":userList=userRepo.findByAlamatContainingIgnoreCase(value);break;
            case "email":userList=userRepo.findByEmailContainingIgnoreCase(value);break;
            case "namaLengkap":userList=userRepo.findByNamaLengkapContainingIgnoreCase(value);break;
            case "noHp":userList=userRepo.findByNoHpContainingIgnoreCase(value);break;
            case "umur":userList=userRepo.cariUmur(value);break;
            default:userList=userRepo.findAll();
        }
        List<RespUserDTO> listRespUser = convertToListRespUserDTO(userList);
        if (listRespUser.isEmpty()) {
//            GlobalFunction.manualResponse(response,GlobalFunction.dataTidakDitemukan(request));
            return;
        }
        sBuild.setLength(0);
        String headerKey = "Content-Disposition";
        sBuild.setLength(0);

        String headerValue = sBuild.append("attachment; filename=user_").
                append( new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss.SSS").format(new Date())).//audit trails lewat nama file nya
                        append(".xlsx").toString();//buat excel
        response.setHeader(headerKey, headerValue);
        response.setContentType("application/octet-stream");

        Map<String,Object> map = GlobalFunction.convertClassToObject(new RespUserDTO());
        List<String> listTampungSebentar = new ArrayList<>();
        for (Map.Entry<String,Object> entry : map.entrySet()) {
            listTampungSebentar.add(entry.getKey());
        }
        int intListTampungSebentar = listTampungSebentar.size();
        String [] headerArr = new String[intListTampungSebentar];
        String [] loopDataArr = new String[intListTampungSebentar];
        for (int i = 0; i < intListTampungSebentar; i++) {
            headerArr[i] = GlobalFunction.camelToStandar(String.valueOf(listTampungSebentar.get(i))).toUpperCase();//BIASANYA JUDUL KOLOM DIBUAT HURUF BESAR DENGAN FORMAT STANDARD
            loopDataArr[i] = listTampungSebentar.get(i);
        }
        String[][] strBody = new String[listRespUser.size()][intListTampungSebentar];
        for (int i = 0; i < listRespUser.size(); i++) {
            map = GlobalFunction.convertClassToObject(listRespUser.get(i));
            for (int j = 0; j < intListTampungSebentar; j++) {
                strBody[i][j] = String.valueOf(map.get(loopDataArr[j]));
            }
        }
        new ExcelWriter(strBody, headerArr,"sheet-1", response);
    }


    public void generateToPDF(String column, String value, HttpServletRequest request, HttpServletResponse response){
        List<User> userList = null;
//        Map<String,Object> payloadJwt = GlobalFunction.claimsTokenBody(request);
        switch (column){
            case "alamat":userList=userRepo.findByAlamatContainingIgnoreCase(value);break;
            case "email":userList=userRepo.findByEmailContainingIgnoreCase(value);break;
            case "namaLengkap":userList=userRepo.findByNamaLengkapContainingIgnoreCase(value);break;
            case "noHp":userList=userRepo.findByNoHpContainingIgnoreCase(value);break;
            case "umur":userList=userRepo.cariUmur(value);break;
//            case "umur":page=userRepo.cariUmur(pageable,value);break;
            default:userList=userRepo.findAll();
        }
        List<RespUserDTO> listRespMenu = convertToListRespUserDTO(userList);
        if (listRespMenu.isEmpty()) {
//            GlobalFunction.manualResponse(response,GlobalFunction.dataTidakDitemukan(request));
            return;
        }
        Map<String,Object> map = new HashMap<>();
        String strHtml = null;
        Context context = new Context();
        Map<String,Object> mapColumnName = GlobalFunction.convertClassToObject(new RespUserDTO());
        List<String> listTampungSebentar = new ArrayList<>();
        List<String> listHelper = new ArrayList<>();//untuk mapping otomatis di html nya
        for (Map.Entry<String,Object> entry : mapColumnName.entrySet()) {
            listTampungSebentar.add(GlobalFunction.camelToStandar(entry.getKey()));
            listHelper.add(entry.getKey());
        }
        Map<String,Object> mapTampung = null;
        List<Map<String,Object>> listMap = new ArrayList<>();
        for (int i = 0; i < listRespMenu.size(); i++) {
            mapTampung = GlobalFunction.convertClassToObject(listRespMenu.get(i),null);
            listMap.add(mapTampung);
        }

        map.put("listKolom",listTampungSebentar);
        map.put("listContent",listMap);
        map.put("listHelper",listHelper);
        map.put("timestamp",GlobalFunction.formatingDateDDMMMMYYYY());
//        map.put("username",payloadJwt.get("namaLengkap"));
        map.put("username","Paul");
        map.put("totalData",listRespMenu.size());
        map.put("title","REPORT USER");
        context.setVariables(map);
        strHtml = springTemplateEngine.process("global-report",context);
        pdfGenerator.htmlToPdf(strHtml,"user",response);
    }

    public User convertToEntiy(ValUserDTO valUserDTO){
        return modelMapper.map(valUserDTO, User.class);
    }

    public User convertToEntiy(ValLoginDTO valLoginDTO){
        return modelMapper.map(valLoginDTO, User.class);
    }

    public List<RespUserDTO> convertToListRespUserDTO(List<User> users){
        return modelMapper.map(users,new TypeToken<List<RespUserDTO>>(){}.getType());
    }



}