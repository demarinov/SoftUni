package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public class MuscleCar extends BaseCar{

    // The cubic centimeters for this type of car are 5000.
    // The minimum horsepower is 400 and the maximum horsepower is 600.
    private static final double cubicCentimeters = 5000d;
    private static final int MIN_HORSE_POWER = 400;
    private static final int MAX_HORSE_POWER = 600;

    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, cubicCentimeters);

        if (horsePower < MIN_HORSE_POWER || horsePower > MAX_HORSE_POWER) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER,
                    horsePower));
        }
    }
}
