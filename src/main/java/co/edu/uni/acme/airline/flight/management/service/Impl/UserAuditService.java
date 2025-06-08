package co.edu.uni.acme.airline.flight.management.service.Impl;

import co.edu.uni.acme.aerolinea.commons.dto.UserAuditDto;
import co.edu.uni.acme.aerolinea.commons.entity.UserAuditEntity;
import co.edu.uni.acme.aerolinea.commons.entity.UserEntity;
import co.edu.uni.acme.airline.flight.management.repository.UserAuditRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAuditService {

    private final UserAuditRepository userAuditRepository;

    @Transactional
    public  void saveAuditUser(UserAuditDto userAudit){
        try{
            UserAuditEntity userAuditEntity = new UserAuditEntity();
            UserEntity userEntity = new UserEntity();
            userEntity.setCodeUser(userAudit.getCodeUser().getCodeUser());
            userAuditEntity.setCodeUser(userEntity);
            userAuditEntity.setAction(userAudit.getAction());
            userAuditEntity.setEventTimestamp(OffsetDateTime.now());
            userAuditRepository.save(userAuditEntity);
        }catch (Exception ex){
            log.error("Error en la auditoria : " + ex.getMessage());
        }
    }


}
