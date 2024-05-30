using DesafioHospedagem;

Console.OutputEncoding = System.Text.Encoding.UTF8;

var guests = new List<Person>();

var person1 = new Person("João", "Silva");
var person2 = new Person("Maria", "Pereira");

guests.Add(person1);
guests.Add(person2);

var suite = new Suite("Standard", 2, 100);
var booking = new Booking(10);

booking.AddSuite(suite);
booking.AddGuests(guests);

Console.WriteLine(booking.GetGuestsCount());
Console.WriteLine(booking.GetDiaryValue());
