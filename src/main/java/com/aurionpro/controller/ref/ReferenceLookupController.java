package com.aurionpro.controller.ref;

import com.aurionpro.entity.ref.RefBusinessPartyType;
import com.aurionpro.entity.ref.RefDocumentType;
import com.aurionpro.entity.ref.RefReasonCode;
import com.aurionpro.repository.ref.RefBusinessPartyTypeRepository;
import com.aurionpro.repository.ref.RefDocumentTypeRepository;
import com.aurionpro.repository.ref.RefReasonCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ref")
@RequiredArgsConstructor
public class ReferenceLookupController {

    private final RefDocumentTypeRepository refDocumentTypeRepository;
    private final RefBusinessPartyTypeRepository refBusinessPartyTypeRepository;
    private final RefReasonCodeRepository refReasonCodeRepository;

    @GetMapping("/document-types")
    public List<RefDocumentType> docTypes() {
        return refDocumentTypeRepository.findAll();
    }

    @GetMapping("/business-party-types")
    public List<RefBusinessPartyType> partyTypes() {
        return refBusinessPartyTypeRepository.findAll();
    }

    @GetMapping("/reason-codes")
    public List<RefReasonCode> reasonCodes() {
        return refReasonCodeRepository.findAll();
    }
}
