package util;

public class StringUtil {

    public static int toInteger(String input) throws NumberFormatException {
        int inputNumber;
        try {
            inputNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자만 입력해야 합니다.");
        }
        return inputNumber;
    }
}
