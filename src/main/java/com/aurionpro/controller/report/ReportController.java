package com.aurionpro.controller.report;

import com.aurionpro.dto.request.ReportFilterRequest;
import com.aurionpro.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/org/{orgId}/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/export/pdf")
    public ResponseEntity<byte[]> exportPdf(@PathVariable Long orgId,
                                            @RequestBody @Valid ReportFilterRequest filter) {
        filter.setOrganizationId(orgId);
        byte[] bytes = reportService.generatePdf(filter);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment().filename("report.pdf").build());
        headers.setContentType(MediaType.APPLICATION_PDF);
        return ResponseEntity.ok().headers(headers).body(bytes);
    }

    @PostMapping("/export/excel")
    public ResponseEntity<byte[]> exportExcel(@PathVariable Long orgId,
                                              @RequestBody @Valid ReportFilterRequest filter) {
        filter.setOrganizationId(orgId);
        byte[] bytes = reportService.generateExcel(filter);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment().filename("report.xlsx").build());
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        return ResponseEntity.ok().headers(headers).body(bytes);
    }
}
