using UnitTestModule.Services;

namespace UnitTestModuleTests;

public class IsEvenTest
{
    [Fact]
    public void ShouldReturnTrueIfNumberIsEven()
    {
        var result = Even.IsEven(2);
        Assert.True(result);
    }

    [Fact]
    public void ShouldReturnFalseIfNumberIsOdd()
    {
        var result = Even.IsEven(3);
        Assert.False(result);
    }

    [Theory]
    [InlineData(new int[] { 2, 4 })]
    [InlineData(new int[] { 6, 8, 10 })]
    public void ShouldReturnTrueIfNumberIsEvenWithTheory(int[] numbers)
    {
       Assert.All(numbers, number => Assert.True(Even.IsEven(number)));
    }
}
