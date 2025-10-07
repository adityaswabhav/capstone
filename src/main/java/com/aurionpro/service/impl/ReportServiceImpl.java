package com.aurionpro.service.impl;

import com.aurionpro.dto.request.ReportFilterRequest;
import com.aurionpro.service.ReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Override
    public byte[] generatePdf(ReportFilterRequest filter) {
        // TODO: Implement iText/Jasper here
        return new byte[0];
    }

    @Override
    public byte[] generateExcel(ReportFilterRequest filter) {
        // TODO: Implement Apache POI here
        return new byte[0];
    }
}
