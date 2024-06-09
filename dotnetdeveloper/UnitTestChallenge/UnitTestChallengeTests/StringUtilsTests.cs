using UnitTestChallenge.Services;

namespace UnitTestChallengeTests;

public class StringUtilsTests
{
    [Fact]
    public void ShouldReturnCount()
    {
        const string text = "Hello";
        const int expected = 5;

        var result = StringUtils.ReturnCount(text);

        Assert.Equal(expected, result);
    }

    [Fact]
    public void ShouldReturnFirstCharacter()
    {
        const string text = "Hello";
        const string expected = "H";

        var result = StringUtils.ReturnFirstCharacter(text);

        Assert.Equal(expected, result);
    }

    [Fact]
    public void ShouldReturnLastCharacter()
    {
        const string text = "Hello";
        const string expected = "o";

        var result = StringUtils.ReturnLastCharacter(text);

        Assert.Equal(expected, result);
    }

    [Fact]
    public void ShouldContainCharacter()
    {
        const string text = "Hello";
        const char character = 'l';
        const bool expected = true;

        var result = StringUtils.ContainCharacter(text, character);

        Assert.Equal(expected, result);
    }

    [Fact]
    public void ShouldStartWithCharacter()
    {
        const string text = "Hello";
        const char character = 'H';
        const bool expected = true;

        var result = StringUtils.StartWithCharacter(text, character);

        Assert.Equal(expected, result);
    }

    [Fact]
    public void ShouldEndWithCharacter()
    {
        const string text = "Hello";
        const char character = 'o';
        const bool expected = true;

        var result = StringUtils.EndWithCharacter(text, character);

        Assert.Equal(expected, result);
    }
}
