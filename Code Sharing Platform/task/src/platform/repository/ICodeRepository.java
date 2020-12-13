package platform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import platform.entity.Code;

@Repository
public interface ICodeRepository extends JpaRepository<Code, String> {

    @Query("SELECT c FROM Code c WHERE c.timeRestriction = ?1 " +
            "AND c.viewsRestriction = ?1 ORDER BY c.orderTicket DESC")
    Page<Code> findLatest(boolean timeRestriction, boolean viewsRestriction,
                          PageRequest pageable);
}

