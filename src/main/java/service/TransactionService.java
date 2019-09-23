package service;

import exception.TransactionException;
import java.sql.SQLException;
import java.util.List;
import pojo.TransactionPojo;

public interface TransactionService extends BaseService {

  Integer initiateTransaction(TransactionPojo transactionPojo) throws TransactionException;

  TransactionPojo doTransaction(Integer transactionId) throws TransactionException;

  List<TransactionPojo> getTransactions() throws TransactionException;
}
