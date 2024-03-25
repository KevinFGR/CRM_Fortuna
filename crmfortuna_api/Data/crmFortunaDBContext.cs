using crmfortuna_api.Data.Map;
using crmfortuna_api.Models;
using Microsoft.EntityFrameworkCore;

namespace crmfortuna_api.Data;

public class crmFortunaDBContext : DbContext
{
    public crmFortunaDBContext(DbContextOptions<crmFortunaDBContext> options) : base(options){
        
    }

    public DbSet<UserModel> Users {get; set;}
    public DbSet<ClientModel> Clients {get;set;}

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.ApplyConfiguration(new UserMap());
        modelBuilder.ApplyConfiguration(new ClientMap());

        base.OnModelCreating(modelBuilder);
    }
}