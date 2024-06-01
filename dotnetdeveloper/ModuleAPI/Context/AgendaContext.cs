using Microsoft.EntityFrameworkCore;
using ModuleAPI.Entities;

namespace ModuleAPI.Context;

public class AgendaContext(DbContextOptions<AgendaContext> options) : DbContext(options)
{
    public DbSet<Contact> Contacts { get; set; }
}
