import org.junit.Assert;
import org.junit.Test;

public class StringCalculatorTest {

    StringCalculator calculator = new StringCalculator();

    @Test
    public void inputEmptyStringReturnsZero() throws Exception {

        Assert.assertEquals(0, calculator.add(""));
    }

    @Test
    public void inputNumericalStringReturnsThatNumber() throws Exception {


        Assert.assertEquals(1, calculator.add("1"));
        Assert.assertEquals(3, calculator.add("3"));
    }

    @Test
    public void inputTwoNumbersReturnTheSumOfThoseNumber() throws Exception {


        Assert.assertEquals(3, calculator.add("1,2"));
        Assert.assertEquals(8, calculator.add("3,5"));

    }

    @Test
    public void inputHandlesTrailingWhiteSpace() throws Exception {


        Assert.assertEquals(5, calculator.add("1,4 "));
    }

    @Test
    public void inputNewLineBreaksAndCommasShouldBeInterchangeableBetweenNumbers() throws Exception {

        Assert.assertEquals(6, calculator.add("1,2\n3"));
        Assert.assertEquals(20, calculator.add("3\n5\n3,9"));

    }

    @Test
    public void inputSupportDifferentDelimitersWithASeparateLineAtBeginningOfString() throws Exception {

        Assert.assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test(expected = Exception.class)
    public void inputNegativeNumberWillThrowAnException() throws Exception {

        calculator.add("-1,2,-3");
    }

    @Test
    public void inputNegativeNumberWillThrowAnExceptionWithMessage() {

        String message = "";
        try {
            calculator.add("-1,2,-3");
        } catch (Exception e) {
            message = e.getMessage();
        }

        Assert.assertEquals("Negatives Not Allowed: -1, -3", message);

    }

    @Test
    public void inputNumbersGreaterThan1000ShouldBeIgnored() throws Exception {

        Assert.assertEquals(2, calculator.add("1000,1001,2"));
    }

    @Test
    public void inputDelimitersCanBeOfAnyLength() throws Exception{

        Assert.assertEquals(6, calculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void inputAllowMultipleDelimiters() throws Exception{

        Assert.assertEquals(6, calculator.add("//[*][%]\n1*2%3"));

    }

    @Test
    public void inputHandleMultipleDeliminatorsWithALengthLongerThanOneCharacter()  throws Exception{

        Assert.assertEquals(10, calculator.add("//[***][#][%]\n1***2#3%4"));
    }

    @Test
    public void inputHandleDelimiterThatHaveNumberAsPartOfThemWhereNumberCannotBeOnEdgeOfDelimiter() throws Exception {

        Assert.assertEquals(6, calculator.add("//[*1*][#][%]\n1*1*2%3"));
    }
}