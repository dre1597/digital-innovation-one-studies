namespace DesafioApi.Models;

public class TaskEntity
{
    public int Id { get; set; }
    public string Title { get; set; }
    public string Description { get; set; }
    public DateTime Date { get; set; }
    public Status Status { get; set; }
}
