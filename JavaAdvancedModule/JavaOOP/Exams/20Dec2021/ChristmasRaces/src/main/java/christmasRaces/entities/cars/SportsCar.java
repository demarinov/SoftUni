package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public class SportsCar extends BaseCar{

    private static final double cubicCentimeters = 3000d;
    private static final int MIN_HORSE_POWER = 250;
    private static final int MAX_HORSE_POWER = 450;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, cubicCentimeters);

        if (horsePower < MIN_HORSE_POWER || horsePower > MAX_HORSE_POWER) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER,
                    horsePower));
        }
    }
}
