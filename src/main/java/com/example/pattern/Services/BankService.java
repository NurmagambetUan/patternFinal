package com.example.pattern.Services;


import com.example.pattern.Repositories.BankRepository;
import com.example.pattern.builder.*;
import com.example.pattern.chainOfResp.*;
import com.example.pattern.composite.CompositeBankIAccount;
import com.example.pattern.exceptions.ErrorCode;
import com.example.pattern.exceptions.ServiceException;
import com.example.pattern.factory.AccountFactory;
import com.example.pattern.factory.CurrentIAccount;
import com.example.pattern.factory.IAccount;
import com.example.pattern.factory.SavingIAccount;
import com.example.pattern.models.entities.Bank;
import com.example.pattern.proxy.Authority;
import com.example.pattern.proxy.ProxyAuthority;
import com.example.pattern.state.Context;
import com.example.pattern.state.StartState;
import com.example.pattern.state.StopState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<Bank> findAll() {
        return bankRepository.findAllByDeletedAtIsNull();
    }

    public Bank findById(Long id) throws ServiceException {
        Optional<Bank> mealOptional = Optional.ofNullable(bankRepository.findByIdAndDeletedAtIsNull(id));
        return mealOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("account not found")
                .build());
    }

    public Bank update(Bank bank) throws ServiceException {
        if (bank.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("account is null")
                    .build();
        }
        return bankRepository.save(bank);
    }

    public Bank add(Bank bank) throws ServiceException {
        if (bank.getId() != null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("account already exists")
                    .build();
        }
        return bankRepository.save(bank);
    }


    public boolean invest(Long id) throws ServiceException {
        Bank bank = findById(id);
        String selected = "not selectprod";
        String invest = "not invest";
        if (bank != null) {
            selected = "selectprod";
            if (bank.getInvested() == null) {
                invest = "invest";
            } else {
                if (!bank.getInvested()) {
                    invest = "invest";
                }
            }
            BankHandler bankHandler1 = new InvestSelection();
            BankHandler bankHandler4 = new Invest();
            bankHandler1.nextHandler(bankHandler4);
            BankAction bankAction = new BankAction(selected);
            bankHandler1.processRequest(bankAction);
            bankAction = new BankAction(invest);
            bank.setInvested(bankHandler4.processRequest(bankAction));
            bankRepository.save(bank);
            return bankHandler4.processRequest(bankAction);
        } else {
            return false;
        }
    }

    public boolean confirm(Long id) throws ServiceException {
        Bank bank = findById(id);
        String selected = "not selectprod";
        String invest = "not invest";
        if (bank != null) {
            selected = "selectprod";
            if (bank.getInvested() != null) {
                if (bank.getInvested()) {
                    invest = "invest";
                }
            }
            BankHandler bankHandler1 = new InvestSelection();
            BankHandler bankHandler4 = new Invest();
            BankHandler bankHandler5 = new Confirmation();

            bankHandler1.nextHandler(bankHandler4);
            bankHandler4.nextHandler(bankHandler5);

            BankAction bankAction = new BankAction(selected);
            bankHandler1.processRequest(bankAction);
            bankAction = new BankAction(invest);
            bank.setInvested(bankHandler4.processRequest(bankAction));
            bankAction = new BankAction("confirm");
            bank.setConfirmed(bankHandler5.processRequest(bankAction));


            Context context = new Context();

            StartState startState = new StartState();
            bank.setCompleted(startState.doAction(context)); // set false


            bankRepository.save(bank);

            return bankHandler5.processRequest(bankAction);
        } else {
            return false;
        }
    }

    public String complete(Long id) throws ServiceException {
        Bank bank = findById(id);
        Context context = new Context();

        StopState stopState = new StopState();
        bank.setCompleted(stopState.doAction(context));
        bankRepository.save(bank);
        return "Invest #" + bank.getId() + " completed";
    }


    public void delete(Bank bank) throws ServiceException {
        if (bank.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("account is null")
                    .build();
        }
    }

    public String checkAuthority(String login) {
        Authority authority = new ProxyAuthority();
        try {
            return authority.connectTo(login);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Object changeAccountInfo(String login) {
        AccountFactory accountFactory = new AccountFactory();
        try {
            IAccount savingIAccount = accountFactory.getAccount("SAVING");
            savingIAccount.accountType();
            IAccount currentIAccount = accountFactory.getAccount("CURRENT");
            currentIAccount.accountType();
            return accountFactory.getAccount("Saved");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Object changeAllAccountInfo(String login) {
        CompositeBankIAccount compositeBankIAccount = new CompositeBankIAccount();
        try {
            //Saving Accounts
            SavingIAccount savingAccount1 = new SavingIAccount();
            SavingIAccount savingAccount2 = new SavingIAccount();

            //Current Account
            CurrentIAccount currentAccount1 = new CurrentIAccount();
            CurrentIAccount currentAccount2 = new CurrentIAccount();

            //Composite Bank Account
            CompositeBankIAccount compositeBankAccount1 = new CompositeBankIAccount();
            CompositeBankIAccount compositeBankAccount2 = new CompositeBankIAccount();
            CompositeBankIAccount compositeBankAccount = new CompositeBankIAccount();

            //Composing the bank accounts
            compositeBankAccount1.add(savingAccount1);
            compositeBankAccount1.add(currentAccount1);

            compositeBankAccount2.add(currentAccount2);
            compositeBankAccount2.add(savingAccount2);

            compositeBankAccount.add(compositeBankAccount2);
            compositeBankAccount.add(compositeBankAccount1);

            compositeBankAccount.accountType();
            return compositeBankAccount.add(savingAccount2);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

