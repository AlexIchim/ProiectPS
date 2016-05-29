using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace BankApplication.Models
{
    public class LoggerModel
    {
        [Key]
        public int LogID { get; set; }
        
        [Required]
        public string userNameLog { get; set; }

        [Required]
        public string transactionLog { get; set; }

        [Required]
        public DateTime dateLog { get; set; }
    }
    public class LoggerDBContext : DbContext
    {
        public DbSet<LoggerModel> Loggers { get; set; }
    }
}