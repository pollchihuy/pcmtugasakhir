package coid.bcafinance.pcmtugasakhir.repo;

import coid.bcafinance.pcmtugasakhir.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepo extends JpaRepository<Menu,Long> {

}
