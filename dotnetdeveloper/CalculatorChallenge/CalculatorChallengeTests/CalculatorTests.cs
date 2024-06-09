using CalculatorChallenge;

namespace CalculatorChallengeTests;

public class CalculatorTests
{
    [Theory]
    [InlineData(3, 5, 8)]
    [InlineData(0, 0, 0)]
    public void ShouldSum(int a, int b, int result)
    {
        var calculator = _createCalculator();
        var sum = calculator.Sum(a, b);
        Assert.Equal(result, sum);
    }

    [Theory]
    [InlineData(3, 5, -2)]
    [InlineData(0, 0, 0)]
    public void ShouldSubtract(int a, int b, int result)
    {
        var calculator = _createCalculator();
        var subtract = calculator.Subtract(a, b);
        Assert.Equal(result, subtract);
    }

    [Theory]
    [InlineData(3, 5, 15)]
    [InlineData(0, 0, 0)]
    public void ShouldMultiply(int a, int b, int result)
    {
        var calculator = _createCalculator();
        var multiply = calculator.Multiply(a, b);
        Assert.Equal(result, multiply);
    }

    [Theory]
    [InlineData(4, 2, 2)]
    [InlineData(3, 5, 0)]
    public void ShouldDivide(int a, int b, int result)
    {
        var calculator = _createCalculator();
        var divide = calculator.Divide(a, b);
        Assert.Equal(result, divide);
    }

    [Fact]
    public void ShouldThrowExceptionWhenDivideByZero()
    {
        var calculator = _createCalculator();
        Assert.Throws<DivideByZeroException>(() => calculator.Divide(3, 0));
    }

    [Fact]
    public void ShouldGetEmptyHistory()
    {
        var calculator = _createCalculator();
        var history = calculator.GetHistory();
        Assert.Empty(history);
    }

    [Fact]
    public void ShouldGetLastOperationOnHistory()
    {
        var calculator = _createCalculator();
        calculator.Sum(3, 5);
        var history = calculator.GetHistory();
        Assert.Single(history);
    }

    [Fact]
    public void ShouldGetLast2OperationsOnHistory()
    {
        var calculator = _createCalculator();
        calculator.Sum(3, 5);
        calculator.Subtract(3, 5);
        var history = calculator.GetHistory();
        Assert.Equal(2, history.Count);
    }

    [Fact]
    public void ShouldGetLast3OperationsOnHistory()
    {
        var calculator = _createCalculator();

        calculator.Sum(3, 5);
        calculator.Subtract(3, 5);
        calculator.Multiply(3, 5);
        calculator.Divide(3, 5);

        var history = calculator.GetHistory();
        Assert.Equal(3, history.Count);
    }

    private static Calculator _createCalculator()
    {
        return new Calculator();
    }
}
