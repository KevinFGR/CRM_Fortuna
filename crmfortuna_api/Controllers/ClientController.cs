using crmfortuna_api.Applications.Interfaces;
using crmfortuna_api.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Infrastructure;

namespace crmfortuna_api.Controllers;

[ApiController]
[Route("api/[controller]")]

public class ClientController : ControllerBase
{
    private readonly IClientApplication _clientApplication;
    public ClientController(IClientApplication clientApplication)
    {
        _clientApplication = clientApplication;
    }

    [HttpGet]
    public async Task<IActionResult> GetAllClients(){
        try{
        ClientModel[] clients = await _clientApplication.FindAllClients();
        if(clients == null){
            return NoContent();
        }

        return Ok(clients);
        }
        catch (Exception ex){
            return this.StatusCode(StatusCodes.Status500InternalServerError,
            $"Error trying get all clients. Message: {ex.Message}");
        }
    }

    [HttpGet("{id}")]
    public async Task<IActionResult> GetClientById(int id){
        try{
            ClientModel client = await _clientApplication.FindClientById(id);
            if(client == null){
                return NoContent();
            }

            return Ok(client);
        }
        catch(Exception ex){
            return this.StatusCode(StatusCodes.Status500InternalServerError,
            $"Error trying get client. Message: {ex.Message}");
        }
    }

    [HttpGet("name/{name}")]
    public async Task<IActionResult> GetClientByName(string name){
        try{
            ClientModel[] clients = await _clientApplication.FindAllClientByName(name);
            if(clients == null){
                return NoContent();
            }
            return Ok(clients);
        }
        catch(Exception ex){
            return this.StatusCode(StatusCodes.Status500InternalServerError,
            $"Error trying get clients by name. Message: {ex.Message}");
        }
    }

    [HttpPost]
    public async Task<IActionResult> AddClient(ClientModel newClient){
        try{
            await _clientApplication.AddClient(newClient);
            return this.StatusCode(StatusCodes.Status201Created);
        }
        catch(Exception ex){
            return this.StatusCode(StatusCodes.Status500InternalServerError,
            $"Something went wront trying to register new client. Error message: {ex.Message}");
        }
    }

    [HttpPut]
    public async Task<IActionResult> UpadateClient(ClientModel model, int id){
        try{
            var request = await _clientApplication.UpdateClient(model, id);
            if(request == null){
                return NoContent();
            }
            return Ok(model);
        }
        catch(Exception ex){
            return this.StatusCode(StatusCodes.Status500InternalServerError,
            $"Something went wrong trying Update client. Error message: {ex}");
        }
    }

    [HttpDelete]
    public async Task<IActionResult> DeleteClient(int id){
        try{
            var request = await _clientApplication.DeleteClient(id);
            if(request != true){
                return this.StatusCode(StatusCodes.Status500InternalServerError,
                    $"Something went wront deleting this client.");
            }
            return Ok("Client successfuly deleted.");
        }
        catch(Exception ex){
            return this.StatusCode(StatusCodes.Status500InternalServerError,
                $"Something went wront deleting this client. Error message: {ex.Message}");
        }
    }

}