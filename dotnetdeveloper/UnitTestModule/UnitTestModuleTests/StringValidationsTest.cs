using UnitTestModule.Services;

namespace UnitTestModuleTests;

public class StringValidationsTest
{
    [Fact]
    public void ShouldCountCharacters5()
    {
        var result = StringValidations.CountCharacters("5");
        Assert.Equal(1, result);
    }
}
