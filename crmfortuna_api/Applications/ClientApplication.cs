using crmfortuna_api.Applications.Interfaces;
using crmfortuna_api.Data;
using crmfortuna_api.Models;
using Microsoft.EntityFrameworkCore;

namespace crmfortuna_api.Applications
{
    public class ClientApplication : IClientApplication
    {
        private readonly crmFortunaDBContext _context;
        public ClientApplication(crmFortunaDBContext context)
        {
            _context = context;
            _context.ChangeTracker.QueryTrackingBehavior = QueryTrackingBehavior.NoTracking;

        }


        public async Task<ClientModel[]> FindAllClients()
        {
            return await _context.Clients.ToArrayAsync();
        }

        public async Task<ClientModel> FindClientById(int id)
        {
            return await _context.Clients.FirstOrDefaultAsync(client=> client.Id==id);
        }
        public async Task<ClientModel[]> FindAllClientByName(string name)
        {
            return await _context.Clients.OrderBy(client=>client.Id)
                                         .Where(client=>client.Name.ToLower()
                                         .Contains(name.ToLower()))
                                         .ToArrayAsync();                 
        }

        public async Task<ClientModel> AddClient(ClientModel model)
        {
            await _context.Clients.AddAsync(model);
            await _context.SaveChangesAsync();
            
            return model;
        }
        public async Task<ClientModel> UpdateClient(ClientModel model, int id)
        {
            ClientModel client = await FindClientById(id);
            if(client == null){
                throw new Exception($"Theres no client with Id = {id}");
            } 

            model.Id = client.Id;
            _context.Clients.Update(model);
            await _context.SaveChangesAsync();

            return model;
        }

        public async Task<bool> DeleteClient(int id)
        {
            ClientModel client = await FindClientById(id);
            if(client == null){
                throw new Exception($"There's no client with Id = {id}");
            }

            _context.Clients.Remove(client);
            await _context.SaveChangesAsync();

            return true;
        }
    }
}