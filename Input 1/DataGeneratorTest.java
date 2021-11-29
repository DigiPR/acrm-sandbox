package ch.fhnw.acrm.config;

import ch.fhnw.acrm.business.service.AgentService;
import ch.fhnw.acrm.business.service.PartnerService;
import ch.fhnw.acrm.data.domain.Agent;
import ch.fhnw.acrm.data.domain.Partner;
import ch.fhnw.acrm.data.domain.PartnerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Profile("test")
@Configuration
public class DataGeneratorTest {

    @Autowired
    private AgentService agentService;
    @Autowired
    private PartnerService partnerService;

    @PostConstruct
    private void init() throws Exception {
        demoUser();
        demoPartner();
    }

    private void demoUser() throws Exception {
        Agent agentUser = new Agent();
        agentUser.setEmail("user@user.com");
        agentUser.setPassword("password");
        agentUser.setName("user");
        agentService.saveAgent(agentUser);
    }

    private void demoPartner() {
        Partner partner = new Partner();
        partner.setName("Partner, Inc.");
        PartnerAddress partnerAddress = new PartnerAddress();
        partnerAddress.setPlace("Partner place");
        partnerAddress.setStreet("Partner street");
        partner.getPartnerAddresses().add(partnerAddress);
        partnerService.enterBusinessPartnership(partner);
    }
}
