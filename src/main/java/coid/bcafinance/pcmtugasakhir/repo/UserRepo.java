package coid.bcafinance.pcmtugasakhir.repo;

import coid.bcafinance.pcmtugasakhir.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {


    /** SELECT TOP 1 FROM mst_user ORDER BY id DESC */
    /** SELECT * FROM mst_user ORDER BY id DESC LIMIT 0,1 */
    Optional<User> findTopByOrderByIdDesc();

    /** SELECT  * FROM MstUser WHERE value LIKE CONCAT('%',LOWER(?),'%')*/
    Page<User> findByAlamatContainingIgnoreCase(Pageable pageable, String value);
    Page<User> findByEmailContainingIgnoreCase(Pageable pageable, String value);
    Page<User> findByNamaLengkapContainingIgnoreCase(Pageable pageable, String value);
    Page<User> findByNoHpContainingIgnoreCase(Pageable pageable, String value);

    /** change when migration
     * SELECT u FROM User u WHERE
     * CAST(DATE_DIFF(years,u.tanggalLahir,CURRENT_TIMESTAMP) AS VARCHAR) LIKE '%?%'
     *
     * SELECT * FROM MstUser
     */
    @Query(value = "SELECT  x FROM User x WHERE CAST(DATEDIFF(year ,CURRENT_TIMESTAMP ,x.tanggalLahir) AS STRING ) LIKE CONCAT('%',?1,'%') ")
    Page<User> cariUmur(Pageable pageable, String value);

    List<User> findByAlamatContainingIgnoreCase(String value);
    List<User> findByEmailContainingIgnoreCase(String value);
    List<User> findByNamaLengkapContainingIgnoreCase(String value);
    List<User> findByNoHpContainingIgnoreCase(String value);
//    List<User> findByNoHpContainingIgnoreCaseAAndCreatedAtBetween(String value,String dateAwal, String dateAkhir);

    @Query(value = "SELECT  x FROM User x WHERE CAST(DATEDIFF(year ,CURRENT_TIMESTAMP ,x.tanggalLahir) AS STRING ) LIKE CONCAT('%',?1,'%') ")
    List<User> cariUmur(String value);
    
    
    
}