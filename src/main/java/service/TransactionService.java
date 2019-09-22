package service;

import exception.TransactionException;
import java.util.List;
import pojo.TransactionPojo;

public interface TransactionService extends BaseService {
   TransactionPojo doTransaction() throws TransactionException;
   List<TransactionPojo> getTransactions() throws  TransactionException;
}
