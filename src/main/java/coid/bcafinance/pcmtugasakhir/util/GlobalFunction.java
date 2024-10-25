package coid.bcafinance.pcmtugasakhir.util;

import coid.bcafinance.pcmtugasakhir.handler.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalFunction {


    public static ResponseEntity<Object> dataBerhasilDisimpan(HttpServletRequest request){
        return new ResponseHandler().
                generateResponse("DATA BERHASIL DISIMPAN",
                        HttpStatus.CREATED,
                        null,null,request);
    }

    public static ResponseEntity<Object> dataGagalDisimpan(String errorCode,HttpServletRequest request){
        return new ResponseHandler().
                generateResponse("DATA GAGAL DISIMPAN",
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        null,errorCode,request);
    }

    public static ResponseEntity<Object> dataBerhasilDiubah(HttpServletRequest request){
        return new ResponseHandler().
                generateResponse("DATA BERHASIL DIUBAH",
                        HttpStatus.OK,
                        null,null,request);
    }

    public static ResponseEntity<Object> dataTidakDitemukan(HttpServletRequest request){
        return new ResponseHandler().
                generateResponse("DATA TIDAK DITEMUKAN",
                        HttpStatus.NOT_FOUND,
                        null,"G-01-002",request);
    }
}
