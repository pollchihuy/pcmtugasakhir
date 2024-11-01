package coid.bcafinance.pcmtugasakhir.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Entity
@Table(name = "MstUser")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="Username",unique=true,length=16)
    private String username;
    /** GANTI SAAT MIGRASI */
    @Column(name="Email",unique=true,length=50)
    private String email;
    @Column(name="NoHp",unique=true,length=20)//+62
    private String noHp;
    @Column(name="Password",length=64)
    private String password;
    @Column(name = "CreatedBy",updatable=false,nullable=false)
    private Long createdBy=1L;
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "ModifiedBy",insertable = false)
    private Long modifiedBy;
    @Column(name = "ModifiedAt")
    @UpdateTimestamp
    private Date modifiedAt;
    @Column(name = "TanggalLahir")
    private LocalDate tanggalLahir;
    @Column(name = "OTP",length=64)
    private String otp;
    @Transient
    private Integer umur;
    @Column(name = "Alamat")
    private String alamat;//length default 255
    @Column(name = "NamaLengkap", length = 50)
    private String namaLengkap;

    @ManyToOne
    @JoinColumn(name = "IDAccess", foreignKey = @ForeignKey(name = "fk-to-akses"))
    private Akses akses;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Menu> lt = this.akses.getMenuList();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Menu menu : lt) {
            grantedAuthorities.add(new SimpleGrantedAuthority(menu.getNama()));
        }

        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public Akses getAkses() {
        return akses;
    }

    public void setAkses(Akses akses) {
        this.akses = akses;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public LocalDate getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(LocalDate tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Integer getUmur() {
        return Period.
                between(tanggalLahir,LocalDate.now()).getYears();
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
