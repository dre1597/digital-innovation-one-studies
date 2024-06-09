using Microsoft.EntityFrameworkCore;
using DesafioApi.Models;

namespace DesafioApi.Context;

public class OrganizerContext(DbContextOptions<OrganizerContext> options) : DbContext(options)
{
    public DbSet<TaskEntity> Tasks { get; set; }
}
