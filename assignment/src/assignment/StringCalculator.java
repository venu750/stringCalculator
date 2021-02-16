package assignment;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class StringCalculator {
    /**
     * String calculator
     * @param text
     * @return sum of elements
     * @throws MyOwnRuntimeException
     */
    public int add(String text) throws MyOwnRuntimeException {
        if (text.isEmpty()) {
            return 0;
        } else {
            String[] tokens = createTokens(text);
            List<Integer> numbers = new ArrayList<>();
            ensureAllNonNegativeNumbers(numbers);
            stringsToIntegerConversion(tokens, numbers);
            return numbers.stream().reduce(0, Integer::sum);
        }
    }

    /**
     * takes sequence of strings, converts into Integers and add to list
     * @param tokens
     * @param numbers
     */

    private void stringsToIntegerConversion(String[] tokens, List<Integer> numbers) {
        for (String i : tokens) {
            numbers.add(toInt(i));
        }
    }

    /**
     * Take list of integers,if any negative number is present then throws RTE
     * @param numbers
     * @throws MyOwnRuntimeException
     */
    private void ensureAllNonNegativeNumbers(List<Integer> numbers) throws MyOwnRuntimeException {
        List<Integer>negativeNumbers=new ArrayList<>();
        List<Integer>positiveNumbers=new ArrayList<>();
        numbers.stream().forEach(i -> (i < 0 ? negativeNumbers:positiveNumbers).add(i));
        if(negativeNumbers.isEmpty()){
            throw  new MyOwnRuntimeException("negatives are not allowed");
        }
    }

    /**
     * converts string to integer
     * @param i
     * @return
     */
    private Integer toInt(String i) {
        return Integer.parseInt(i);
    }

    /**
     * take input string and create string tokens
     * @param text
     * @return
     */
    private String[] createTokens(String text) {
        if (text.startsWith("//")) {
            return splitUsingCustomDelimiter(text);
        } else {
            return splitUsingNewLineAndCommas(text);
        }
    }

    /**
     * take input string and create string tokens
     * @param text
     * @return
     */
    private String[] splitUsingNewLineAndCommas(String text) {
        return text.split(",|\n");
    }
    /**
     * take input string and create string tokens
     * @param text
     * @return
     */
    private String[] splitUsingCustomDelimiter(String text) {
        Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(text);
        matcher.matches();
        String customDelimiter = matcher.group(1);
        String numbers = matcher.group(2);
        return numbers.split(Pattern.quote(customDelimiter));
    }
}
