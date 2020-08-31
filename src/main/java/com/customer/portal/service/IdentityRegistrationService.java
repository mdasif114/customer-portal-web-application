package com.customer.portal.service;

import com.customer.portal.entities.Identity;
import com.customer.portal.entities.User;
import com.customer.portal.repositories.IdentityRepository;
import com.customer.portal.validators.IdentityRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.customer.portal.utils.Constants.IDENTITY;
import static com.customer.portal.utils.Constants.USERID;

@Slf4j
@Service
public class IdentityRegistrationService {

    @Autowired
    private IdentityRepository identityRepo;

    @Autowired
    HttpSession session;

    /**
     * This method is to fetch Identity details of user from database.
     * and returns the data in transfer object
     *
     * @return user identity details DTO object
     */
    public IdentityRegistrationDto getIdentityDetails() {
        log.info("Get Identity Details. ");
        IdentityRegistrationDto identityDto = new IdentityRegistrationDto();
        Identity identity = getIdentityData();
        if (identity != null) {
            identityDto.setDocumentType(identity.getDocumentType());
            identityDto.setDocumentNumber(identity.getDocumentNumber());
            session.setAttribute(IDENTITY, createSessionObj(identityDto));
        }
        return identityDto;
    }

    private Identity getIdentityData() {
        Long userId = (Long) session.getAttribute(USERID);
        Identity identity = (Identity) session.getAttribute(IDENTITY);
        if (identity == null) {
            Optional<Identity> identityDataFromDb = identityRepo.findById(userId);
            if (identityDataFromDb.isPresent()) {
                log.info("Retrieve data from database: " + identityDataFromDb);
                identity = identityDataFromDb.get();
            }
        }
        return identity;
    }

    /**
     * This method is to store Identity details of user in database
     *
     * @param identityRegistrationDto it contains the identity details of user.
     */
    public void saveIdentityDetails(IdentityRegistrationDto identityRegistrationDto) {
        Identity identity = createIdentityEntityData(identityRegistrationDto);
        Identity identityObjForSession = createSessionObj(identityRegistrationDto);
        if (!identityObjForSession.equals(session.getAttribute(IDENTITY))) {
            identityRepo.save(identity);
            log.info("Identity saved successfully in database.");
            session.setAttribute(IDENTITY, identityObjForSession);
        }
    }

    private Identity createIdentityEntityData(IdentityRegistrationDto identityDto) {
        Long userId = (Long) session.getAttribute(USERID);
        log.info("UserId from session: " + userId);
        Identity identity = new Identity();
        identity.setDocumentNumber(identityDto.getDocumentNumber());
        identity.setDocumentType(identityDto.getDocumentType());
        identity.setIdentityId(userId);
        identity.setUser(new User(userId));
        return identity;
    }

    private Identity createSessionObj(IdentityRegistrationDto identityDto) {
        return new Identity(identityDto.getDocumentNumber(), identityDto.getDocumentType());
    }
}
