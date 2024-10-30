package coid.bcafinance.pcmtugasakhir.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "MstMenu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8)
    private String nama;
    private String path;// /akses

    @ManyToMany(mappedBy = "menuList")
    private List<Akses> aksesList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
