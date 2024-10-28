package coid.bcafinance.pcmtugasakhir.util;


import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.pdf.PdfWriter;
//import com.juaracoding.config.OtherConfig;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Untuk Generate dari HTML (Thymeleaf) ke PDF
 */
@Component
public class PdfGenerator {
    private String [] strExceptionArr = new String[2];
    private StringBuilder sBuild = new StringBuilder();
    private ServletOutputStream os;

    /**
     *
     * @param html - thymeleaf yang sudah dirender menjadi html (Object-object nya sudah dimapping)
     * @param prefixFile - awalan dari nama file nanti selanjutnya digunakan timestamp agar ada rekam jejak nya
     * @return seluruh data ini akan diproses
     */
//    public Map<String,Object> htmlToPdf(String html, String prefixFile) {
//        Map<String,Object> map = new HashMap<>();
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
//        ConverterProperties converterProperties = new ConverterProperties();
//        DefaultFontProvider defaultFontProvider = new DefaultFontProvider();
//        FileOutputStream fileOutputStream = null;
//        String fileName = "";
//        try{
//            converterProperties.setFontProvider(defaultFontProvider);
//            HtmlConverter.convertToPdf(html,pdfWriter,converterProperties);
//            sBuild.setLength(0);
//            fileName = sBuild.append(prefixFile).append("_").
//                    append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())).
//                    append(".pdf").toString();
////            System.out.println("Full Path : "+ OtherConfig.getPathGeneratePDF()+fileName);
//
//        }
//        finally {
//            try {
//                fileOutputStream = new FileOutputStream(OtherConfig.getPathGeneratePDF()+fileName);
//                byteArrayOutputStream.writeTo(fileOutputStream);
//                byteArrayOutputStream.close();
//                byteArrayOutputStream.flush();
//                fileOutputStream.close();
//            } catch (IOException e) {
//                LoggingFile.exceptionStringz("PdfGenerator","htmlToPDF",e,OtherConfig.getFlagLogging());
//                throw new RuntimeException(e);
//            }
//        }
//        return map;
//    }

    /**
     *
     * @param html
     * @param prefixFile
     * @param response
     */
    public void htmlToPdf(String html, String prefixFile, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
        ConverterProperties converterProperties = new ConverterProperties();
        DefaultFontProvider defaultFontProvider = new DefaultFontProvider();
        try{
            converterProperties.setFontProvider(defaultFontProvider);
            HtmlConverter.convertToPdf(html,pdfWriter,converterProperties);
            sBuild.setLength(0);
            String fileName = sBuild.append(prefixFile).append("_").
                    append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())).
                    append(".pdf").toString();
            response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
            response.setContentType("application/pdf");
        }
        finally {
            try{
                os = response.getOutputStream();
                byteArrayOutputStream.writeTo(os);
                byteArrayOutputStream.close();
                byteArrayOutputStream.flush();
                os.close();
            }catch (Exception e){
//                LoggingFile.exceptionStringz("PdfGenerator","htmlToPdf", e, OtherConfig.getFlagLogging());
            }
        }
    }
}