// See https://aka.ms/new-console-template for more information

using DesafioEstacionamento;

Console.OutputEncoding = System.Text.Encoding.UTF8;

Console.WriteLine("Welcome");

Console.WriteLine("What is the initial price?");
var initialValue = Convert.ToDecimal(Console.ReadLine());

Console.WriteLine("What is the price per hour?");
var pricePerHour = Convert.ToDecimal(Console.ReadLine());

var parkingLot = new ParkingLot(initialValue, pricePerHour);

var showMenu = true;

while (showMenu)
{
    Console.Clear();
    Console.WriteLine("Options");
    Console.WriteLine("1 - Add vehicle");
    Console.WriteLine("2 - Remove vehicle");
    Console.WriteLine("3 - List vehicles");
    Console.WriteLine("4 - Exit");

    switch (Console.ReadLine())
    {
        case "1":
            parkingLot.AddVehicle();
            break;
        case "2":
            parkingLot.RemoveVehicle();
            break;
        case "3":
            parkingLot.ListVehicles();
            break;
        case "4":
            showMenu = false;
            break;
        default:
            Console.WriteLine("Invalid option");
            break;
    }

    Console.WriteLine("Press any key to continue");
    Console.ReadKey();
}

Console.WriteLine("Good bye");
