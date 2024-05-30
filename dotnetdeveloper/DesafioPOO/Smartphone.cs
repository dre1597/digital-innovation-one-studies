namespace DesafioPOO;

public abstract class Smartphone(string number, string model, int memory, int storage)
{
    public string Number { get; set; } = number;
    public string Model { get; set; } = model;
    public int Memory { get; set; } = memory;
    public int Storage { get; set; } = storage;

    public void Call()
    {
        Console.WriteLine("Calling...");
    }

    public void Answer()
    {
        Console.WriteLine("Answering...");
    }

    public abstract void InstallApp(string appName);
}
