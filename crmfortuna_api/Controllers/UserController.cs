using crmfortuna_api.Applications.Interfaces;
using crmfortuna_api.Models;
using Microsoft.AspNetCore.Mvc;

namespace crmfortuna_api.Controllers;

[ApiController]
[Route("api/[controller]")]
public class UserController : ControllerBase
{
    private readonly IUserAppllication _userApplication;

    public UserController(IUserAppllication userApplication)
    {
        _userApplication = userApplication;
    }

    [HttpGet]
    public async Task<IActionResult> GetAllUsers(){
        try{
            UserModel[] allUsers = await _userApplication.FindAllUsers();
            if(allUsers == null){
                return NoContent();
            }
            
            return Ok(allUsers);
        }
        catch(Exception ex){
            return this.StatusCode(StatusCodes.Status500InternalServerError,
                $"Something wrong ocurred trying get all users. Error message: {ex.Message}");
        }
    }

    [HttpGet("{id}")]
    public async Task<IActionResult> GetUserById(int id){
        try{
            UserModel user = await _userApplication.FindUserById(id);
            if(user == null){
                return NoContent();
            }
            return Ok(user);
        }
        catch(Exception ex){
            return this.StatusCode(StatusCodes.Status500InternalServerError,
                $"Something wrong ocurred getting user by id = {id}. Error message: {ex.Message}");
        }
    }

    [HttpGet("login/{login}")]
    public async Task<IActionResult> GetAllUserByLogin(string login){
        try{
            UserModel[] users = await _userApplication.FindAllUserByLogin(login);
            if(users == null){
                return NoContent();
            }
            return Ok(users);
        }
        catch(Exception ex){
            return this.StatusCode(StatusCodes.Status500InternalServerError,
                $"An error ocurred trying get users by login = {login}. Erro message: {ex.Message}");
        }
    }

    [HttpPost]
    public async Task<IActionResult> PostUser(UserModel newUser){
        try{
            await _userApplication.AddUser(newUser);
            return Ok(newUser);
        }
        catch(Exception ex){
            return this.StatusCode(StatusCodes.Status500InternalServerError,
                $"An erro ocurred trying to post this new user. Error message: {ex.Message}");
        }
    }

    [HttpPut("{id}")]
    public async Task<IActionResult> UpdateUser(UserModel user, int id){
        try{
            var request = await _userApplication.UpdateUser(user, id);
            if (request==null){
                return BadRequest();
            }
            return Ok(user);
        }
        catch(Exception ex){
            return this.StatusCode(StatusCodes.Status500InternalServerError,
                $"Something went wrong upadating client. Error mesage: {ex.Message}");
        }
    }
    
    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteUser(int id){
        try{
            bool request = await _userApplication.DeleteUser(id);
            if(request != true){
                return BadRequest();
            }
            return Ok();
        }
        catch(Exception ex){
            return this.StatusCode(StatusCodes.Status500InternalServerError,
                $"An error ocurred trying to delete this user. Error message: {ex.Message}");
        }
    }

}
