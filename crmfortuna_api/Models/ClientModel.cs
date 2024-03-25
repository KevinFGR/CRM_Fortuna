namespace crmfortuna_api.Models
{
    public class ClientModel
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Email { get; set; }
        public string Phone { get; set; }
        public string CPF_CNPJ { get; set; }
        public string Product { get; set; }
        public string Contracted_plan {get; set;}
        public int Channels { get; set; }
        public decimal Price { get; set; }
        public string Description { get; set; }
    }
}