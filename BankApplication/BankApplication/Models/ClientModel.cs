using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace BankApplication.Models
{


    public class ClientModel
    {
        [Key]
        public int ClientID { get; set; }

        [Required]
        public string ClientName { get; set; }
       
        [Required]
        public string CNP { get; set; }
        
        [Required]
        public string address { get; set; }

    }
     public class ClientDBContext : DbContext
    {
         public DbSet<ClientModel> Clients { get; set; }
    }
}