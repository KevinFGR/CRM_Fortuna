using crmfortuna_api.Models;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;

namespace crmfortuna_api.Applications.Interfaces
{
    public interface IUserAppllication
    {
         Task<UserModel[]> FindAllUsers();
         Task<UserModel> FindUserByLogin(string userName);
         Task<UserModel> FindUserById(int id);
         Task<UserModel> AddUser(UserModel model);
         Task<UserModel> UpdateUser(UserModel model, int id);
         Task<bool> DeleteUser(int id);

    }
}