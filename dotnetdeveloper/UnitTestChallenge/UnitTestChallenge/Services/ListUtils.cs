namespace UnitTestChallenge.Services;

public static class ListUtils
{
    public static List<int> RemoveNegativeNumbers(List<int> list)
    {
        return list.Where(x => x > 0).ToList();
    }

    public static bool ContainNumber(List<int> list, int number)
    {
        return list.Contains(number);
    }

    public static List<int> MultiplyListByNumber(List<int> list, int number)
    {
        return list.Select(x => x * number).ToList();
    }

    public static int ReturnMaxNumber(List<int> list)
    {
        return list.Max();
    }

    public static int ReturnMinNumber(List<int> list)
    {
        return list.Min();
    }
}
