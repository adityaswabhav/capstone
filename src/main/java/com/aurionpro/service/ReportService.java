package com.aurionpro.service;

import com.aurionpro.dto.request.ReportFilterRequest;

public interface ReportService {
    byte[] generatePdf(ReportFilterRequest filter);   // iText/Jasper under the hood
    byte[] generateExcel(ReportFilterRequest filter); // Apache POI under the hood
}
