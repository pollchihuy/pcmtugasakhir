package coid.bcafinance.pcmtugasakhir.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Entity
@Table(name = "LogUser")
public class LogUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LogId")
    private Long logId;
    private Long id;
    @Column(name = "Username", length = 16)
    private String username;
    /**
     * GANTI SAAT MIGRASI
     */
    @Column(name = "Email", length = 50)
    private String email;
    @Column(name = "NoHp", length = 19)//+62
    private String noHp;
    @Column(name = "Password", length = 64)
    private String password;
    @Column(name = "CreatedBy", updatable = false, nullable = false)
    private Long createdBy = 1L;
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "TanggalLahir")
    private LocalDate tanggalLahir;
    @Column(name = "Alamat")
    private String alamat;//length default 255
    @Column(name = "NamaLengkap", length = 50)
    private String namaLengkap;

    @Column(name = "Flag", nullable = false)
    private Character flag;

    @Column(name = "IDAccess")
    private Long akses;
}