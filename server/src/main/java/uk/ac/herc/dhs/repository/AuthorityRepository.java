package uk.ac.herc.dhs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.herc.dhs.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
