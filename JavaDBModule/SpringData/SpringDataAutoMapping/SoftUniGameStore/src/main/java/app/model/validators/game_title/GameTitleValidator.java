package app.model.validators.game_title;

import app.constants.ValidationMessages;
import app.model.validators.ValidatorsUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class GameTitleValidator implements ConstraintValidator<GameTitle, CharSequence> {

    private static final Pattern PATTERN_CAPITAL_FIRST = Pattern.compile("^(?=[A-Z]).*$");

    private int minLength;
    private int maxLength;
    private boolean capitalFirstLetter;
    private boolean nullable;

    @Override
    public void initialize(GameTitle constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
        this.maxLength = constraintAnnotation.maxLength();
        this.capitalFirstLetter = constraintAnnotation.capitalFirstLetter();
        this.nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        if (value == null || value.length() == 0) {
            return nullable;
        }

        if (this.capitalFirstLetter && !PATTERN_CAPITAL_FIRST.matcher(value).matches()) {
            ValidatorsUtil.setErrorMessage(context,
                    ValidationMessages.GAME_TITLE_SHOULD_START_WITH_CAPITAL_LETTER);
            return false;
        }

        if (value.length() < this.minLength) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.GAME_TITLE_TOO_SHORT);
            return false;
        }

        if (value.length() > this.maxLength) {
            ValidatorsUtil.setErrorMessage(context, ValidationMessages.GAME_TITLE_TOO_LONG);
            return false;
        }

        return true;
    }
}
