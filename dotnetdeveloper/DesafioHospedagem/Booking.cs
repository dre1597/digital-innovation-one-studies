namespace DesafioHospedagem;

public class Booking
{
    private List<Person> Guests { get; set; }
    private Suite Suite { get; set; }
    private int ReservedDays { get; set; }

    public Booking() {}

    public Booking(int reservedDays)
    {
        ReservedDays = reservedDays;
    }

    public void AddGuests(List<Person> guests)
    {
        if (Suite.Capacity < guests.Count)
        {
            throw new Exception("Capacity exceeded");
        }

        Guests = guests;
    }

    public void AddSuite(Suite suite)
    {
        Suite = suite;
    }

    public int GetGuestsCount()
    {
        return Guests.Count;
    }

    public decimal GetDiaryValue()
    {
        var value = ReservedDays * Suite.Diary;

        if (ReservedDays >= 10)
        {
            value *= 0.9m;
        }

        return value;
    }
}
