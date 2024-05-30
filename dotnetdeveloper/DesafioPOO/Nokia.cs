namespace DesafioPOO;

public class Nokia(string number, string model, int memory, int storage)
    : Smartphone(number, model, memory, storage)
{
    public override void InstallApp(string appName)
    {
        Console.WriteLine($"Installing {appName} on Nokia...");
    }
}
