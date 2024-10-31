package coid.bcafinance.pcmtugasakhir.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MstAkses")
public class Akses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NamaAkses",unique = true , length = 30)
    private String namaAkses;

    @Column(name = "CreatedBy",updatable=false,nullable=false)
    private Long createdBy=1L;
    @CreationTimestamp
    @Column(name = "CreatedAt")
    private Date createdAt;
    @Column(name = "ModifiedBy",insertable = false)
    private Long modifiedBy;
    
    @Column(name = "ModifiedAt")
    @UpdateTimestamp
    private Date modifiedAt;

    @ManyToMany
    @JoinTable(name = "MapAksesMenu",
            uniqueConstraints = {
            @UniqueConstraint(name = "unique-akses-menu",
                    columnNames = { "IDAkses", "IDMenu" }) },
            joinColumns = @JoinColumn(name = "IDAkses",foreignKey = @ForeignKey(name = "fk_to_map_akses")),
            inverseJoinColumns =@JoinColumn(name = "IDMenu", foreignKey =@ForeignKey(name = "fk_to_map_menu")))
    private List<Menu> menuList;

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaAkses() {
        return namaAkses;
    }

    public void setNamaAkses(String namaAkses) {
        this.namaAkses = namaAkses;
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
}
