using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace BankApplication.Models
{
    public class TransactionModel
    {
        [Key]
        public int TransactionID { get; set; }

        [Required]
        public int AccountFromID { get; set; }

        [Required]
        public int AccountToID { get; set; }

        [Required]
        public int TransactionAmount { get; set; }

        [Required]
        public DateTime TransactionDate { get; set; }
    }

    public class TransactionDBContext : DbContext
    {
        public DbSet<TransactionModel> Transactions { get; set; }
    }
}