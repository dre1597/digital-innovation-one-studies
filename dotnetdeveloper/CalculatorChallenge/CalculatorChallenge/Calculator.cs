namespace CalculatorChallenge;

public class Calculator
{
    private readonly List<string> _history = [];

    public int Sum(int a, int b)
    {
        var result = a + b;
        _history.Insert(0, $"{_getNow()} - Result: {a} + {b} = {result}");

        return result;
    }

    public int Subtract(int a, int b)
    {
        var result = a - b;
        _history.Insert(0, $"{_getNow()} - Result: {a} - {b} = {result}");

        return result;
    }

    public int Multiply(int a, int b)
    {
        var result = a * b;
        _history.Insert(0, $"{_getNow()} - Result: {a} * {b} = {result}");

        return result;
    }

    public int Divide(int a, int b)
    {
        var result = a / b;
        _history.Insert(0, $"{_getNow()} - Result: {a} / {b} = {result}");

        return result;
    }

    public List<string> GetHistory()
    {
        if(_history.Count <= 3) return _history;

        _history.RemoveRange(3, _history.Count - 3);
        return _history;
    }

    private static string _getNow()
    {
        return DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
    }
}
