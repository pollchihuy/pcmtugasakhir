package coid.bcafinance.pcmtugasakhir.dto.validasi;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ValAksesDTO {

    @NotBlank
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[\\w\\s]{5,30}$")
    @JsonProperty("nama-akses")
    private String namaAkses;

    public @NotBlank @NotNull @NotEmpty @Pattern(regexp = "^[\\w\\s]{5,30}$") String getNamaAkses() {
        return namaAkses;
    }

    public void setNamaAkses(@NotBlank @NotNull @NotEmpty @Pattern(regexp = "^[\\w\\s]{5,30}$") String namaAkses) {
        this.namaAkses = namaAkses;
    }
}