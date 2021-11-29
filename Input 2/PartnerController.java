package ch.fhnw.acrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/partner")
public class PartnerController {

    @GetMapping
    public String getPartnerView(){
        return "acrm/partner.html";
    }

    @GetMapping("/create")
    public String getPartnerCreateView(){
        return "../acrm/partnerCreate.html";
    }
}
