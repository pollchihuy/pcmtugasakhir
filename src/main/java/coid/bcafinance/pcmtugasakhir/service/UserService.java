package coid.bcafinance.pcmtugasakhir.service;


import coid.bcafinance.pcmtugasakhir.core.IService;
import coid.bcafinance.pcmtugasakhir.dto.validasi.ValLoginDTO;
import coid.bcafinance.pcmtugasakhir.dto.validasi.ValUserDTO;
import coid.bcafinance.pcmtugasakhir.handler.ResponseHandler;
import coid.bcafinance.pcmtugasakhir.model.User;
import coid.bcafinance.pcmtugasakhir.repo.UserRepo;
import coid.bcafinance.pcmtugasakhir.util.GlobalFunction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Platform Code : AUT
 * Modul Code : 004
 *
 */
@Service
public class UserService implements IService<User> {

    @Autowired
    private UserRepo userRepo;

    private ModelMapper modelMapper = new ModelMapper();

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
        userNext.setModifiedBy(1L);

        return GlobalFunction.dataBerhasilDiubah(request);
    }

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        return null;
    }

    public User convertToEntiy(ValUserDTO valUserDTO){
        return modelMapper.map(valUserDTO, User.class);
    }

    public User convertToEntiy(ValLoginDTO valLoginDTO){
        return modelMapper.map(valLoginDTO, User.class);
    }
}
