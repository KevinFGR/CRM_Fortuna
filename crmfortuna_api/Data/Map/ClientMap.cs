using crmfortuna_api.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace crmfortuna_api.Data.Map
{
    public class ClientMap : IEntityTypeConfiguration<ClientModel>
    {
        public void Configure(EntityTypeBuilder<ClientModel> builder)
        {
            builder.HasKey(client => client.Id);
            builder.Property(client =>client.Name).IsRequired().HasMaxLength(150);
            builder.Property(client =>client.Email).IsRequired().HasMaxLength(50);
            builder.Property(client =>client.Phone).IsRequired().HasMaxLength(20);
            builder.Property(client =>client.CPF_CNPJ).IsRequired().HasMaxLength(20);
            builder.Property(client =>client.Product).IsRequired().HasMaxLength(20);
            builder.Property(client =>client.Contracted_plan).IsRequired().HasMaxLength(20);
            builder.Property(client =>client.Positions).IsRequired();
            builder.Property(client =>client.Channels).IsRequired();
            builder.Property(client =>client.Price).IsRequired();
            builder.Property(client =>client.Description).IsRequired().HasMaxLength(500);
        }
    }
}