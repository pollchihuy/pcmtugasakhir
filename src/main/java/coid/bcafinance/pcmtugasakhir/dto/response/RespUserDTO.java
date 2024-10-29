package coid.bcafinance.pcmtugasakhir.dto.response;


import coid.bcafinance.pcmtugasakhir.model.Akses;

import java.util.Date;

public class RespUserDTO {
    private Long id;
    private String username;
    private String email;
    private String noHp;
    private String alamat;
    private Integer umur;
    private String namaLengkap;
    private RespAksesDTO akses;

    public RespAksesDTO getAkses() {
        return akses;
    }

    public void setAkses(RespAksesDTO akses) {
        this.akses = akses;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
}
