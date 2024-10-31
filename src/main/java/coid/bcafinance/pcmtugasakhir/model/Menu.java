package coid.bcafinance.pcmtugasakhir.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "MstMenu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20,name = "Nama",nullable = false,unique = true)
    private String nama;

    @Column(length = 30,name = "Path",nullable = false,unique = true)
    private String path;

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