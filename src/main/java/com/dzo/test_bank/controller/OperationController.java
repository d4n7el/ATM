package com.dzo.test_bank.controller;

import com.dzo.test_bank.model.dto.OperationDetailDto;
import com.dzo.test_bank.model.dto.OperationDto;
import com.dzo.test_bank.model.entity.Operation;
import com.dzo.test_bank.service.IOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class OperationController extends BaseController{

    @Autowired
    IOperation operationService;

    @PostMapping("/operation")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationDto create(@RequestBody @Valid Operation operation) {
        return operationService.save(operation);
    }

    @GetMapping("/operations")
    @ResponseStatus(HttpStatus.CREATED)
    public
    List<OperationDetailDto> operations() {
        return operationService.transactionsDetails();
    }
}
