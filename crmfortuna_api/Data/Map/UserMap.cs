using crmfortuna_api.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace crmfortuna_api.Data.Map
{
    public class UserMap : IEntityTypeConfiguration<UserModel>
    {
        public void Configure(EntityTypeBuilder<UserModel> builder)
        {
            builder.HasKey(user => user.Id);
            builder.Property(user => user.Name).IsRequired().HasMaxLength(150);
            builder.Property(user => user.UserPosition).HasMaxLength(150);
            builder.Property(user => user.Login).IsRequired().HasMaxLength(50);
            builder.Property(user => user.Password).IsRequired().HasMaxLength(20);
        }
    }
}