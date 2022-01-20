package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value="AM")
public class AmoniumChloride extends BasicChemicalIngredient{

    private static final String NAME = "Amonium Chloride";

    private static final BigDecimal PRICE = new BigDecimal("6.12");

    private static final String CHEMICAL_FORMULA = "NB4C1";


    public AmoniumChloride() {
        super(NAME,PRICE,CHEMICAL_FORMULA);
    }

    public static String getNAME() {
        return NAME;
    }

    public static BigDecimal getPRICE() {
        return PRICE;
    }

}
