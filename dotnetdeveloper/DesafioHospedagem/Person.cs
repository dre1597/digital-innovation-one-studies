namespace DesafioHospedagem;

public class Person
{
    public Person() {}

    public Person(string name)
    {
        Name = name;
    }

    public Person(string name, string surname)
    {
        Name = name;
        Surname = surname;
    }

    private string Name { get; set; }
    private string Surname { get; set; }
    private string FullName => $"{Name} {Surname}".ToUpper();
}
