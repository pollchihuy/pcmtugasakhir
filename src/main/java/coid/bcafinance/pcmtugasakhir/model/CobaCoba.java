package coid.bcafinance.pcmtugasakhir.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CobaCoba {

    @Id
    private Long id;
    private String nama;
    private String alamat;

}