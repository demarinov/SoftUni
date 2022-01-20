
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ChainBlockTest {

    private Chainblock chainblock;
    private List<Transaction> transactionList;

    @Before
    public void setUp() {
        chainblock = new ChainblockImpl();
        transactionList = stubTransactions();
    }

    @Test
    public void testContainsTransactionShouldReturnIfTransactionIsPresent() {


        Transaction expectedTransaction = transactionList.get(0);
        assertFalse(chainblock.contains(expectedTransaction));
        chainblock.add(expectedTransaction);
        assertTrue(chainblock.contains(expectedTransaction));

    }

    @Test
    public void testAddTransactionShouldAddCorrectTransaction() {

        Transaction testTransaction = transactionList.get(0);

        chainblock.add(testTransaction);

        assertEquals(1, chainblock.getCount());

    }

    @Test
    public void testAddTransactionShouldNotAddDuplicateTransaction() {

        Transaction testTransaction = transactionList.get(0);

        chainblock.add(testTransaction);
        chainblock.add(testTransaction);

        assertFalse(chainblock.getCount() == 2);

    }

    @Test
    public void testGetCountShouldReturnCountOfAllTransactions() {

        chainblock.add(transactionList.get(0));

        assertEquals(1, chainblock.getCount());
    }

    @Test
    public void testChangeTransactionStatus() {
        Transaction transaction = transactionList.get(0);

        chainblock.add(transaction);
        chainblock.changeTransactionStatus(1, TransactionStatus.FAILED);
        assertEquals(TransactionStatus.FAILED, transaction.getTransactionStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionStatusShouldFailIfIdIsAbsent() {

        Transaction transaction = transactionList.get(0);
        chainblock.add(transaction);
        assertTrue(chainblock.getCount() > 0);
        chainblock.changeTransactionStatus(11, TransactionStatus.FAILED);
    }

    @Test
    public void testRemoveTransactionById() {

        Transaction transaction = transactionList.get(0);
        chainblock.add(transaction);
        chainblock.removeTransactionById(transaction.getId());
        assertTrue(chainblock.getCount() == 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionByIdShouldFailIfIdIsAbsent() {

        Transaction transaction = transactionList.get(0);
        chainblock.add(transaction);
        chainblock.removeTransactionById(transaction.getId()+1);
    }

    @Test
    public void testGetTransactionById() {

        populateTransactions();
        Transaction transaction = chainblock.getById(1);
        assertNotNull(transaction);
        assertEquals(1, transaction.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTransactionByIdShouldFailIfIdIsAbsent() {

        populateTransactions();
        Transaction transaction = chainblock.getById(0);
        assertNotNull(transaction);
        chainblock.getById(11);
    }

    private void populateTransactions() {

        List<Transaction> transactions = stubTransactions();

        for (Transaction transaction : transactions) {
            chainblock.add(transaction);
        }
    }

    private List<Transaction> stubTransactions() {

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new TransactionImpl(1,TransactionStatus.SUCCESSFUL,"Brabara","Rolland",10.0d));
        transactions.add(new TransactionImpl(2,TransactionStatus.SUCCESSFUL,"Brabara2","Rolland",10.0d));
        transactions.add(new TransactionImpl(3,TransactionStatus.SUCCESSFUL,"Brabara3","Rolland",10.0d));
        transactions.add(new TransactionImpl(4,TransactionStatus.SUCCESSFUL,"Brabara4","Rolland",10.0d));
        transactions.add(new TransactionImpl(5,TransactionStatus.SUCCESSFUL,"Brabara5","Rolland",10.0d));
        transactions.add(new TransactionImpl(6,TransactionStatus.SUCCESSFUL,"Brabara6","Rolland",10.0d));
        transactions.add(new TransactionImpl(7,TransactionStatus.SUCCESSFUL,"Brabara7","Rolland",10.0d));
        transactions.add(new TransactionImpl(8,TransactionStatus.SUCCESSFUL,"Brabara8","Rolland",10.0d));
        transactions.add(new TransactionImpl(9,TransactionStatus.SUCCESSFUL,"Brabara9","Rolland",10.0d));
        transactions.add(new TransactionImpl(10,TransactionStatus.SUCCESSFUL,"Brabara10","Rolland",10.0d));

        return transactions;
    }
}
