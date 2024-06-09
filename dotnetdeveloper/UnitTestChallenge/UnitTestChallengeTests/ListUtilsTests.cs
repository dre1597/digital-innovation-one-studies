using UnitTestChallenge.Services;

namespace UnitTestChallengeTests;

public class ListUtilsTests
{
    [Fact]
    public void ShouldRemoveNegativeNumbers()
    {
        var list = new List<int> { 5, -1, -8, 9 };
        var expected = new List<int> { 5, 9 };

        var result = ListUtils.RemoveNegativeNumbers(list);

        Assert.Equal(expected, result);
    }

    [Fact]
    public void ShouldContainNumber()
    {
        var list = new List<int> { 5, 1, 8, 9 };
        const bool expected = true;

        var result = ListUtils.ContainNumber(list, 8);

        Assert.Equal(expected, result);
    }

    [Fact]
    public void ShouldMultiplyListByNumber()
    {
        var list = new List<int> { 5, 1, 8, 9 };
        const int number = 2;
        var expected = new List<int> { 10, 2, 16, 18 };

        var result = ListUtils.MultiplyListByNumber(list, number);

        Assert.Equal(expected, result);
    }

    [Fact]
    public void ShouldReturnMaxNumber()
    {
        var list = new List<int> { 5, 1, 8, 9 };
        const int expected = 9;

        var result = ListUtils.ReturnMaxNumber(list);

        Assert.Equal(expected, result);
    }

    [Fact]
    public void ShouldReturnMinNumber()
    {
        var list = new List<int> { 5, 1, 8, 9 };
        const int expected = 1;

        var result = ListUtils.ReturnMinNumber(list);

        Assert.Equal(expected, result);
    }
}
