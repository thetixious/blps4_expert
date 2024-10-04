package org.tix.lab4_1.repo.mainDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tix.lab4_1.model.mainDB.ExpertMessage;

import java.util.Optional;

@Repository
public interface ExpertMessageRepository extends JpaRepository<ExpertMessage,Long> {
    Optional<ExpertMessage> findByUserId(Long id);
}
