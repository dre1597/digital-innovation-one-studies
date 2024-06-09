using UnitTestModule.Services;

namespace UnitTestModuleTests;

public class CalculatorTest
{
    [Fact]
    public void ShouldSum5And5AndReturn10()
    {
        var result = CalculatorImpl.Sum(5, 5);
        Assert.Equal(10, result);
    }

    [Fact]
    public void ShouldSubtract5And5AndReturn0()
    {
        var result = CalculatorImpl.Subtract(5, 5);
        Assert.Equal(0, result);
    }

    [Fact]
    public void ShouldMultiply5And5AndReturn25()
    {
        var result = CalculatorImpl.Multiply(5, 5);
        Assert.Equal(25, result);
    }

    [Fact]
    public void ShouldDivide5And5AndReturn1()
    {
        var result = CalculatorImpl.Divide(5, 5);
        Assert.Equal(1, result);
    }

    [Fact]
    public void ShouldModulo5And5AndReturn0()
    {
        var result = CalculatorImpl.Modulo(5, 5);
        Assert.Equal(0, result);
    }
}
