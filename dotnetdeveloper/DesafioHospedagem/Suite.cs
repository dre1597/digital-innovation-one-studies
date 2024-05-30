namespace DesafioHospedagem;

public class Suite
{
    public Suite() {}

    public Suite(string type, int capacity, decimal diary)
    {
        Type = type;
        Capacity = capacity;
        Diary = diary;
    }

    public string Type { get; set; }
    public int Capacity { get; set; }
    public decimal Diary { get; set; }
}
