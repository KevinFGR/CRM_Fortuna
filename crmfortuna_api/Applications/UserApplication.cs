using crmfortuna_api.Applications.Interfaces;
using crmfortuna_api.Data;
using crmfortuna_api.Models;
using Microsoft.EntityFrameworkCore;

namespace crmfortuna_api.Applications;

public class UserApplication : IUserAppllication
{
    private readonly crmFortunaDBContext _context;
    public UserApplication(crmFortunaDBContext context)
    {
        _context = context;
        _context.ChangeTracker.QueryTrackingBehavior = QueryTrackingBehavior.NoTracking;
    }
    public async Task<UserModel[]> FindAllUsers()
    {
        return await _context.Users.ToArrayAsync();
    }
    public async Task<UserModel> FindUserById(int id)
    {
        return await _context.Users.FirstOrDefaultAsync(user => user.Id == id);
    }

    public async Task<UserModel[]> FindAllUserByLogin(string userName)
    {
        return await _context.Users
            .OrderBy(user => user.Id)
            .Where(user=>user.Login.ToLower()
            .Contains(userName.ToLower()))
            .ToArrayAsync();
    }


    public async Task<UserModel> AddUser(UserModel model)
    {
        await _context.Users.AddAsync(model);
        await _context.SaveChangesAsync();

        return model;
    }
    public async Task<UserModel> UpdateUser(UserModel model, int id)
    {
        UserModel user = await FindUserById(id);
        if(user == null){
            throw new Exception($"There's no user with Id = {id}");
        }
        
        model.Id = user.Id;

        _context.Users.Update(model);
        await _context.SaveChangesAsync();

        return model;
    }
    public async Task<bool> DeleteUser(int id)
    {
        UserModel user = await FindUserById(id);
        if(user == null){
            throw new Exception($"There's no user with Id = {id}");
        } 

        _context.Users.Remove(user);
        await _context.SaveChangesAsync();

        return true;
    }
}