package com.example.pattern.Controllers;

import com.example.pattern.Services.BankService;
import com.example.pattern.exceptions.ServiceException;
import com.example.pattern.models.entities.Bank;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Bank")
@RequestMapping("/api/bank")
public class BankController extends BaseController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return buildResponse(bankService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> getId(@RequestParam Long id) throws ServiceException {
        return buildResponse(bankService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Bank bank) throws ServiceException {
        return buildResponse(bankService.add(bank),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Bank bank) throws ServiceException {
        bankService.delete(bank);
        return buildResponse("deleted", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Bank bank) throws ServiceException {
        return buildResponse(bankService.update(bank), HttpStatus.OK);
    }

    @GetMapping("/invest")
    public ResponseEntity<?> invest(@RequestParam Long id) throws ServiceException {
        return buildResponse(bankService.invest(id), HttpStatus.OK);
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirmation(@RequestParam Long id) throws ServiceException {
        return buildResponse(bankService.confirm(id), HttpStatus.OK);
    }

    @GetMapping("/complete")
    public ResponseEntity<?> complete(@RequestParam Long id) throws ServiceException {
        return buildResponse(bankService.complete(id), HttpStatus.OK);
    }

    @GetMapping("/checkAuthority")
    public ResponseEntity<?> getAuthority(@RequestParam String login) {
        return buildResponse(bankService.checkAuthority(login), HttpStatus.OK);
    }

    @GetMapping("/changeAccountInfo")
    public ResponseEntity<?> getAccountInfo(@RequestParam String login) {
        return buildResponse(bankService.changeAccountInfo(login), HttpStatus.OK);
    }

    @GetMapping("/changeAllAccountInfo")
    public ResponseEntity<?> getAllAccountInfo(@RequestParam String login) {
        return buildResponse(bankService.changeAllAccountInfo(login), HttpStatus.OK);
    }
}


