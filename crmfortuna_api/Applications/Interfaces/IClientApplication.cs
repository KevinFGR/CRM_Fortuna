using crmfortuna_api.Models;

namespace crmfortuna_api.Applications.Interfaces
{
    public interface IClientApplication
    {
         Task<ClientModel[]> FindAllClients();
         Task<ClientModel[]> FindAllClientByName(string name);
         Task<ClientModel> FindClientById(int id);
         Task<ClientModel> AddClient(ClientModel model);
         Task<ClientModel> UpdateClient(ClientModel model, int id);
         Task<bool> DeleteClient(int id); 
    }
}