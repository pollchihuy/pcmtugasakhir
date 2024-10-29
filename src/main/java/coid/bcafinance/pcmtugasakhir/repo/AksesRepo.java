package coid.bcafinance.pcmtugasakhir.repo;

import coid.bcafinance.pcmtugasakhir.model.Akses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface AksesRepo extends JpaRepository<Akses,Long> {

    Optional<Akses> findTopByOrderByIdDesc();
    Page<Akses> findByNamaAksesContainingIgnoreCase(String namaAkses, Pageable pageable);
    List<Akses> findByNamaAksesContainingIgnoreCase(String namaAkses);
}
