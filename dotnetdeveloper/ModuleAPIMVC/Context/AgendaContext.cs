using Microsoft.EntityFrameworkCore;
using ModuleAPIMVC.Models;

namespace ModuleAPIMVC.Context;

public class AgendaContext(DbContextOptions<AgendaContext> options) : DbContext(options)
{
    public DbSet<Contact> Contacts { get; set; }
}
