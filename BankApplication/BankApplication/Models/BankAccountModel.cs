using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace BankApplication.Models
{
    public class BankAccountModel
    {
        [Key]
        public int AccountID { get; set; }

        [Required]
        public int AccountClientID { get; set; }

        [Required]
        public int AccountBalance { get; set; }

        [Required]
        public string AccountType { get; set; }

        [Required]  
        public DateTime AccountCreationDate { get; set; }

    }
    public class AccountDBContext : DbContext
    {
        public DbSet<BankAccountModel> Accounts { get; set; }

        public System.Data.Entity.DbSet<BankApplication.Models.TransactionModel> TransactionModels { get; set; }
    }
}