package com.dzo.test_bank.controller;

import com.dzo.test_bank.persistence.dto.OperationDetailDto;
import com.dzo.test_bank.persistence.dto.OperationDto;
import com.dzo.test_bank.persistence.model.OperationJpa;
import com.dzo.test_bank.persistence.types.TransactionType;
import com.dzo.test_bank.service.IOperation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OperationController extends BaseController{
    private final IOperation operationService;

    public OperationController(IOperation operationService) {
        this.operationService = operationService;
    }

    @PostMapping("/operation")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationDto create(@RequestBody @Valid OperationJpa operationJpa) {
        return operationService.save(operationJpa);
    }

    @GetMapping("/operations")
    @ResponseStatus(HttpStatus.CREATED)
    public
    List<OperationDetailDto> operations(
            @RequestParam(value = "transactionType", required = false) TransactionType transactionType,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "sourceAccountNum", required = false) String sourceAccountNum,
            @RequestParam(value = "targetAccountNum", required = false) String targetAccountNum
    )
    {
        return operationService.transactionsDetails(transactionType, firstName,sourceAccountNum, targetAccountNum);
    }
}
