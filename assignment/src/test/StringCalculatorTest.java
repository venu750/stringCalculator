package test;

import assignment.StringCalculator;
import assignment.MyOwnRuntimeException;
import org.junit.Assert;
import org.junit.Test;

public class StringCalculatorTest {
    private StringCalculator calc=new StringCalculator();
    @Test
    public void inputIsEmptyString() throws MyOwnRuntimeException {
        Assert.assertEquals(0,calc.add(""));
    }
    @Test
    public void inputLengthsOne() throws MyOwnRuntimeException {
        Assert.assertEquals(1,calc.add("1"));
    }
    @Test
    public void inputIsDelimitedByComma() throws MyOwnRuntimeException {
        Assert.assertEquals(3,calc.add("1,2"));
    }
    @Test
    public void inputIsDelimitedByCommas() throws MyOwnRuntimeException {
        Assert.assertEquals(6,calc.add("1,2,3"));
    }
    @Test
    public void inputContainsNewLineDelimiter() throws MyOwnRuntimeException {
        Assert.assertEquals(6,calc.add("1\n2,3"));
    }
    @Test
    public void inputContainsCustomDelimiter() throws MyOwnRuntimeException {
        Assert.assertEquals(6,calc.add("//;\n1;2;3"));
    }
    @Test
    public void customDelimiterBeAlsoARegExpSpecialChar() throws MyOwnRuntimeException {
        Assert.assertEquals(6,calc.add("//.\n1.2.3"));
    }
    @Test
    public void shouldRaiseExceptionOnNegatives(){
        try{
            calc.add("-1,-2,3");
        }catch (RuntimeException | MyOwnRuntimeException ex){
            ex.printStackTrace();
        }
    }
}
