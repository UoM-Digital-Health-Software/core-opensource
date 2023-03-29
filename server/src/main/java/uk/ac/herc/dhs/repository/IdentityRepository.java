package uk.ac.herc.dhs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import uk.ac.herc.dhs.domain.Identity;

/**
 * Spring Data SQL repository for the Identity entity.
 */
@Repository
public interface IdentityRepository extends JpaRepository<Identity, Long> {
    Optional<Identity> getOneByResponseId(String responseId);

    Optional<Identity> getOneByStudyIdAndParticipantIdAndMachineId(
        String studyId,
        String participantId,
        String machineId
    );
}
