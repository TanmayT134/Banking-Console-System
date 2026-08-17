package com.tanmay.corebanking.model;
import com.tanmay.corebanking.enums.AccountType; import com.tanmay.corebanking.exception.*; import java.math.*; import java.time.*;
public abstract class BankAccount { private final String accountNumber,userId; private final AccountType accountType; private BigDecimal balance; private final LocalDateTime createdAt=LocalDateTime.now();
 protected BankAccount(String n,String u,AccountType t,BigDecimal b){accountNumber=n;userId=u;accountType=t;balance=b;}
 public String getAccountNumber(){return accountNumber;} public String getUserId(){return userId;} public AccountType getAccountType(){return accountType;} public synchronized BigDecimal getBalance(){return balance;} public LocalDateTime getCreatedAt(){return createdAt;}
 public synchronized void deposit(BigDecimal a){validate(a);balance=balance.add(a);} public synchronized void withdraw(BigDecimal a){validate(a);if(a.compareTo(balance)>0)throw new BankingException("Insufficient balance. Available balance: ₹"+balance);balance=balance.subtract(a);} private void validate(BigDecimal a){if(a==null||a.compareTo(BigDecimal.ZERO)<=0)throw new InvalidAmountException("Amount must be greater than zero.");}
 public abstract BigDecimal calculateInterest(); }
