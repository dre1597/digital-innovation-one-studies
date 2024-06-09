namespace UnitTestChallenge.Services;

public static class StringUtils
{
    public static int ReturnCount(string text)
    {
        return text.Length;
    }

    public static string ReturnFirstCharacter(string text)
    {
        return text[..1];
    }

    public static string ReturnLastCharacter(string text)
    {
        return text.Substring(text.Length - 1, 1);
    }

    public static bool ContainCharacter(string text, char character)
    {
        return text.Contains(character);
    }

    public static bool StartWithCharacter(string text, char character)
    {
        return text.StartsWith(character);
    }

    public static bool EndWithCharacter(string text, char character)
    {
        return text.EndsWith(character);
    }

}
