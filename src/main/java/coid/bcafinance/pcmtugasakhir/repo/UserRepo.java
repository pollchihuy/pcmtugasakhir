package coid.bcafinance.pcmtugasakhir.repo;

import coid.bcafinance.pcmtugasakhir.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {


    /** SELECT TOP 1 FROM mst_user ORDER BY id DESC */
    /** SELECT * FROM mst_user ORDER BY id DESC LIMIT 0,1 */
    Optional<User> findTopByOrderByIdDesc();

}