package org.tix.lab4_1.repo.bankDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tix.lab4_1.model.bankDB.Manager;

import java.util.Optional;
@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {
    Optional<Manager> findFirstByStatusFalse();
}
