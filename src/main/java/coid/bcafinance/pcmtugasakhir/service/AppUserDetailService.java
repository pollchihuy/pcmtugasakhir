package coid.bcafinance.pcmtugasakhir.service;


import coid.bcafinance.pcmtugasakhir.dto.response.RespUserDTO;
import coid.bcafinance.pcmtugasakhir.dto.validasi.ValLoginDTO;
import coid.bcafinance.pcmtugasakhir.model.User;
import coid.bcafinance.pcmtugasakhir.repo.UserRepo;
import coid.bcafinance.pcmtugasakhir.security.BcryptImpl;
import coid.bcafinance.pcmtugasakhir.security.Crypto;
import coid.bcafinance.pcmtugasakhir.security.JwtUtility;
import coid.bcafinance.pcmtugasakhir.util.GlobalFunction;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class AppUserDetailService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtility jwtUtility;

    private ModelMapper modelMapper = new ModelMapper();
    public ResponseEntity<Object> doLogin(User user , HttpServletRequest request){

        Optional<User> optionalUser = userRepo.findByUsername(user.getUsername());
        if(!optionalUser.isPresent()){
            return GlobalFunction.dataTidakDitemukan(request);
        }

        User nextUser = optionalUser.get();
        if(!BcryptImpl.verifyHash((user.getPassword()+user.getUsername()),nextUser.getPassword())){
            return GlobalFunction.dataTidakDitemukan(request);
        }

        UserDetails userDetails = loadUserByUsername(user.getUsername());
        /** start jwt config */
        Map<String,Object> mapForClaims = new HashMap<>();
        mapForClaims.put("uid",nextUser.getId());//payload
        mapForClaims.put("ml",nextUser.getEmail());//payload
        mapForClaims.put("nl",nextUser.getNamaLengkap());//payload
        mapForClaims.put("pn",nextUser.getNoHp());//payload
        mapForClaims.put("pw",nextUser.getPassword());//payload
        String token = jwtUtility.generateToken(userDetails,mapForClaims);
        Map<String,Object> m = new HashMap<>();
//        m.put("token",token);
        m.put("token", Crypto.performEncrypt(token));
//        m.put("token", token);
        /** end jwt config */
//        m.put("token", Crypto.performEncrypt(nextUser.getUsername()));//format token custom
//        m.put("menu", convertToListRespMenuDTO(nextUser.getAkses().getMenuList()));
        m.put("menu",null);
//        return  GlobalFunction.data("Login Berhasil",m,request);
        return ResponseEntity.status(HttpStatus.OK).body(m);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> opUser = userRepo.findByUsername(s);
        if(opUser.isEmpty())
        {
            throw new UsernameNotFoundException("TOKEN TIDAK VALID");
//            return null;
        }
        User userNext = opUser.get();

        return new org.springframework.security.core.userdetails.
                User(userNext.getUsername(),userNext.getPassword(),userNext.getAuthorities());
    }

    public User convertToEntiy(ValLoginDTO valLoginDTO){
        return modelMapper.map(valLoginDTO, User.class);
    }

}
