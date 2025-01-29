package com.project.customer.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationDTO {
    private Long loanId;
    private String loanType;
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String maritalStatus;
    private String contactInfo;
    private String address;
    private Long annualSalary;
    private Long loanAmount;
    private Long customerId;
    private Long userId;
} 