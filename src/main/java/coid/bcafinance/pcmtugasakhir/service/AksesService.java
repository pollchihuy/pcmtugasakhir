package coid.bcafinance.pcmtugasakhir.service;


import coid.bcafinance.pcmtugasakhir.core.IFile;
import coid.bcafinance.pcmtugasakhir.core.IService;
import coid.bcafinance.pcmtugasakhir.dto.response.RespAksesDTO;
import coid.bcafinance.pcmtugasakhir.dto.validasi.ValLoginDTO;
import coid.bcafinance.pcmtugasakhir.dto.validasi.ValAksesDTO;
import coid.bcafinance.pcmtugasakhir.model.Akses;
import coid.bcafinance.pcmtugasakhir.repo.CobaCobaRepo;
import coid.bcafinance.pcmtugasakhir.repo.AksesRepo;
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
 * Modul Code : 003
 *
 */
@Service
@Transactional
public class AksesService implements IService<Akses>, IFile<Akses> {

    @Autowired
    private AksesRepo aksesRepo;

    private TransformPagination transformPagination = new TransformPagination();
    private ModelMapper modelMapper = new ModelMapper();
    private StringBuilder sBuild = new StringBuilder();

    @Autowired
    private PdfGenerator pdfGenerator;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Override
    public ResponseEntity<Object> save(Akses akses,HttpServletRequest request) {
        try{
            aksesRepo.save(akses);
        }catch (Exception e) {
            return GlobalFunction.dataGagalDisimpan("FEAUT003001",request);
        }
        return GlobalFunction.dataBerhasilDisimpan(request);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, Akses akses, HttpServletRequest request) {
        try{
            Optional<Akses> optionalAkses = aksesRepo.findById(id);
            if(!optionalAkses.isPresent()) {
                return GlobalFunction.dataTidakDitemukan(request);
            }
            Akses aksesNext = optionalAkses.get();
            aksesNext.setNamaAkses(akses.getNamaAkses());
            aksesNext.setMenuList(akses.getMenuList());
            aksesNext.setModifiedBy(1L);
        }catch (Exception e) {
            return GlobalFunction.dataGagalDisimpan("FEAUT003011",request);//011-020
        }
        return GlobalFunction.dataBerhasilDiubah(request);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            Optional<Akses> optionalAkses = aksesRepo.findById(id);
            if(!optionalAkses.isPresent()) {
                return GlobalFunction.dataTidakDitemukan(request);
            }
            aksesRepo.deleteById(id);
        }catch (Exception e){
            return GlobalFunction.dataGagalDihapus("FEAUT003021",request);//021-030
        }
        return GlobalFunction.dataBerhasilDihapus(request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<Akses> optionalAkses = aksesRepo.findById(id);
        if(!optionalAkses.isPresent()) {
            return GlobalFunction.dataTidakDitemukan(request);
        }
        Akses akses = optionalAkses.get();

        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(akses, RespAksesDTO.class));
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        Page<Akses> page = null;
        List<Akses> list = null;
        switch (columnName){
            case "nama":page= aksesRepo.findByNamaAksesContainingIgnoreCase(value,pageable);break;
//            case "umur":page=aksesRepo.cariUmur(pageable,value);break;
            default:page= aksesRepo.findAll(pageable);
        }
        list = page.getContent();
        if (list.isEmpty()){
            return GlobalFunction.dataTidakDitemukan(request);
        }
        return transformPagination.transformObject(
                new HashMap<>(),
                convertToListRespAksesDTO(list),
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
            aksesRepo.saveAll(convertListWorkBookToListEntity(lt,1L));
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            return GlobalFunction.tidakDapatDiproses("FE002002061",request);
        }
        return GlobalFunction.dataBerhasilDisimpan(request);
    }

    public List<Akses> convertListWorkBookToListEntity(List<Map<String, String>> workBookData, Long aksesId){
        List<Akses> list = new ArrayList<>();
        for (int i = 0; i < workBookData.size(); i++) {
            Map<String, String> map = workBookData.get(i);
            Akses akses = new Akses();
            akses.setNamaAkses(map.get("nama_akses"));
            list.add(akses);
        }
        return list;
    }

    public void downloadReportExcel(String column, String value, HttpServletRequest request, HttpServletResponse response) {
        List<Akses> aksesList = null;
        switch (column){
            case "nama":aksesList= aksesRepo.findByNamaAksesContainingIgnoreCase(value);break;
            default:aksesList= aksesRepo.findAll();
        }
        List<RespAksesDTO> listRespAkses = convertToListRespAksesDTO(aksesList);
        if (listRespAkses.isEmpty()) {
//            GlobalFunction.manualResponse(response,GlobalFunction.dataTidakDitemukan(request));
            return;
        }
        sBuild.setLength(0);
        String headerKey = "Content-Disposition";
        sBuild.setLength(0);

        String headerValue = sBuild.append("attachment; filename=akses_").
                append( new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss.SSS").format(new Date())).//audit trails lewat nama file nya
                        append(".xlsx").toString();//buat excel
        response.setHeader(headerKey, headerValue);
        response.setContentType("application/octet-stream");

        Map<String,Object> map = GlobalFunction.convertClassToObject(new RespAksesDTO());
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
        String[][] strBody = new String[listRespAkses.size()][intListTampungSebentar];
        for (int i = 0; i < listRespAkses.size(); i++) {
            map = GlobalFunction.convertClassToObject(listRespAkses.get(i));
            for (int j = 0; j < intListTampungSebentar; j++) {
                strBody[i][j] = String.valueOf(map.get(loopDataArr[j]));
            }
        }
        new ExcelWriter(strBody, headerArr,"sheet-1", response);
    }


    public void generateToPDF(String column, String value, HttpServletRequest request, HttpServletResponse response){
        List<Akses> aksesList = null;
        switch (column){
            case "nama":aksesList= aksesRepo.findByNamaAksesContainingIgnoreCase(value);break;
            default:aksesList= aksesRepo.findAll();
        }
        List<RespAksesDTO> listRespMenu = convertToListRespAksesDTO(aksesList);
        if (listRespMenu.isEmpty()) {
//            GlobalFunction.manualResponse(response,GlobalFunction.dataTidakDitemukan(request));
            return;
        }
        Map<String,Object> map = new HashMap<>();
        String strHtml = null;
        Context context = new Context();
        Map<String,Object> mapColumnName = GlobalFunction.convertClassToObject(new RespAksesDTO());
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
        map.put("username","Paul");
        map.put("totalData",listRespMenu.size());
        map.put("title","REPORT AKSES");
        context.setVariables(map);
        strHtml = springTemplateEngine.process("global-report",context);
        pdfGenerator.htmlToPdf(strHtml,"akses",response);
    }

    public Akses convertToEntiy(ValAksesDTO valAksesDTO){
        return modelMapper.map(valAksesDTO, Akses.class);
    }

    public Akses convertToEntiy(ValLoginDTO valLoginDTO){
        return modelMapper.map(valLoginDTO, Akses.class);
    }

    public List<RespAksesDTO> convertToListRespAksesDTO(List<Akses> aksess){
        return modelMapper.map(aksess,new TypeToken<List<RespAksesDTO>>(){}.getType());
    }



}