namespace UnitTestModule.Services;

public static class CalculatorImpl
{
    public static int Sum(int a, int b)
    {
        return a + b;
    }

    public static int Subtract(int a, int b)
    {
        return a - b;
    }

    public static int Multiply(int a, int b)
    {
        return a * b;
    }

    public static int Divide(int a, int b)
    {
        return a / b;
    }

    public static int Modulo(int a, int b)
    {
        return a % b;
    }
}
