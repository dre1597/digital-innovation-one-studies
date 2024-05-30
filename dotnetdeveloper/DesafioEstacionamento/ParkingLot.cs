namespace DesafioEstacionamento;

public class ParkingLot(decimal initialValue, decimal pricePerHour)
{
    private readonly List<string> _parkedVehicles = [];

    public void AddVehicle()
    {
        Console.WriteLine("What is the vehicle's license plate?");
        var plate = Console.ReadLine();
        if (plate != null) _parkedVehicles.Add(plate);
    }

    public void RemoveVehicle()
    {
        Console.WriteLine("What is the vehicle's license plate?");
        var plate = Console.ReadLine();


        if (_parkedVehicles.Exists(x => x.ToLower().Equals(plate?.ToLower())))
        {
            Console.WriteLine("How many hours was the vehicle parked?");
            var hours = Convert.ToInt32(Console.ReadLine());
            var total = initialValue + pricePerHour * hours;

            if (plate == null) return;
            _parkedVehicles.Remove(plate);
            Console.WriteLine($"The vehicle {plate} has left the parking lot. The bill is {total}");
        }
        else
        {
            Console.WriteLine("Sorry, this vehicle is not here.");
        }
    }

    public void ListVehicles()
    {
        if (_parkedVehicles.Count > 0)
        {
            Console.WriteLine("List of parked vehicles:");
            foreach (var vehicle in _parkedVehicles)
            {
                Console.WriteLine(vehicle);
            }
        }
        else
        {
            Console.WriteLine("Parking lot is empty.");
        }
    }
}
