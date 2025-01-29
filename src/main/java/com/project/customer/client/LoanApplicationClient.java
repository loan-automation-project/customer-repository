package com.project.customer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.project.customer.dto.LoanApplicationDTO;
import java.util.List;

@FeignClient(name = "loan-application-service", url = "${http://localhost:1093/loanApplication}")
public interface LoanApplicationClient {
    @GetMapping("/loans/customer/{customerId}")
    List<LoanApplicationDTO> getLoansByCustomerId(@PathVariable("customerId") Long customerId);
} 