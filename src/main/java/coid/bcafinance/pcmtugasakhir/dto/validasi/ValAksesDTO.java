package coid.bcafinance.pcmtugasakhir.dto.validasi;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

public class ValAksesDTO {

    @NotBlank
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[\\w\\s]{5,30}$")
    @JsonProperty("nama-akses")
    private String namaAkses;

    @JsonProperty("menu-list")
    private List<ValMenuDTO> menuList;

    public List<ValMenuDTO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<ValMenuDTO> menuList) {
        this.menuList = menuList;
    }

    public @NotBlank @NotNull @NotEmpty @Pattern(regexp = "^[\\w\\s]{5,30}$") String getNamaAkses() {
        return namaAkses;
    }

    public void setNamaAkses(@NotBlank @NotNull @NotEmpty @Pattern(regexp = "^[\\w\\s]{5,30}$") String namaAkses) {
        this.namaAkses = namaAkses;
    }
}
