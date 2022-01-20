import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChainblockImpl implements Chainblock{

    private List<Transaction> transactionList;

    public ChainblockImpl() {
        this.transactionList = new ArrayList<>();
    }

    public int getCount() {
        return transactionList.size();
    }

    public void add(Transaction transaction) {
        if (!contains(transaction.getId())) {
            transactionList.add(transaction);
        }
    }

    public boolean contains(Transaction transaction) {
        return contains(transaction.getId());
    }

    public boolean contains(int id) {
        return transactionList.stream().anyMatch(t -> t.getId() == id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {

        transactionList.stream().filter(t -> t.getId() == id)
                .findFirst().orElseThrow(IllegalArgumentException::new)
                .setTransactionStatus(newStatus);

    }

    public void removeTransactionById(int id) {
        if (contains(id)) {
            Transaction transactionToRemove = transactionList.stream().
                    filter(t -> t.getId() == id)
                    .findFirst().get();
            transactionList.remove(transactionToRemove);
            return;
        }

        throw new IllegalArgumentException();
    }

    public Transaction getById(int id) {
        return transactionList.stream().filter(t -> t.getId() == id)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        return null;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        return null;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        return null;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return null;
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        return null;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        return null;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return null;
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        return null;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        return null;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return null;
    }

    public Iterator<Transaction> iterator() {
        return null;
    }
}
