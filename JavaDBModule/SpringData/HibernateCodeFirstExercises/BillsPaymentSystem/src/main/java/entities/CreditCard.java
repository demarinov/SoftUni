package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue(value="CC")
public class CreditCard extends BillingDetails{

    private static final String type = "CC";

    @Column(name="card_type")
    private String cardType;

    @Column(name="expiration_month")
    private byte expirationMonth;

    @Column(name="expiration_year")
    private short expirationYear;

    public CreditCard() {
        super(type);
    }

    public CreditCard(int number,User owner) {
        super(type, number, owner);
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public byte getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(byte expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public short getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(short expirationYear) {
        this.expirationYear = expirationYear;
    }
}
