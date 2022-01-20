package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="BA")
public class BankAccount extends BillingDetails{

    private static final String type = "BA";

    @Column(name="bank_name")
    private String bankName;

    @Column(name="swift_code")
    private String swiftCode;

    public BankAccount() {
        super(type);
    }

    public BankAccount(int number, User owner) {
        super(type, number, owner);
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
