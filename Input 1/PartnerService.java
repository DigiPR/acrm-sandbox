package ch.fhnw.acrm.business.service;

import ch.fhnw.acrm.data.domain.Partner;
import ch.fhnw.acrm.data.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    public Partner enterBusinessPartnership (Partner partner){
        return partnerRepository.save(partner);
    }

    public List<Partner> myPartnerships(){
        return partnerRepository.findAll();
    }
}
