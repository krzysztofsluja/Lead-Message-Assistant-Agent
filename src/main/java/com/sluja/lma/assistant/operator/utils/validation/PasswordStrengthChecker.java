package com.sluja.lma.assistant.operator.utils.validation;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

import lombok.Getter;

public class PasswordStrengthChecker {

    private static final int STRONG_PASSWORD_STRENGTH = 3;

    public static class PasswordStrengthResult {
        @Getter
        private final boolean isStrong;
        private final List<String> suggestions;

        public PasswordStrengthResult(final boolean isStrong, final List<String> suggestions) {
            this.isStrong = isStrong;
            this.suggestions = suggestions;
        }

        public String getSuggestionsAsString() {
            if (CollectionUtils.isEmpty(suggestions)) {
                return StringUtils.EMPTY;
            }
            final StringBuilder result = new StringBuilder();
            for (int i = 0; i < suggestions.size(); i++) {
                result.append(i + 1).append(". ").append(suggestions.get(i));
                if (i < suggestions.size() - 1) {
                    result.append(", ");
                }
            }
            return result.toString();
        }
    }

    public static PasswordStrengthResult checkPasswordStrength(final String password) {
        final Strength strength = getStrength(password);
        return new PasswordStrengthResult(isStrongPassword(strength), getPasswordCorrectionPropositions(strength));
    }

    private static Strength getStrength(final String password) {
        return new Zxcvbn().measure(password);
    }

    private static boolean isStrongPassword(final Strength passwordStrength) {
        return passwordStrength.getScore() >= STRONG_PASSWORD_STRENGTH;
    }

    private static List<String> getPasswordCorrectionPropositions(final Strength passwordStrength) {
        return passwordStrength.getFeedback().getSuggestions();
    }

}
