package uk.ac.herc.dhs.service.mapper;

import org.mapstruct.*;
import uk.ac.herc.dhs.domain.*;
import uk.ac.herc.dhs.service.dto.IdentityDTO;

/**
 * Mapper for the entity {@link Identity} and its DTO {@link IdentityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface IdentityMapper extends EntityMapper<IdentityDTO, Identity> {

}
