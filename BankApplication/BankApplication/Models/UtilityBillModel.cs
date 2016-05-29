using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace BankApplication.Models
{
    public class UtilityBillModel
    {
        [Key]
        public int UtilityBillID { get; set; }

        [Required]
        public int AccountToPayID { get; set; }

        [Required]
        public int AmountToPay { get; set; }
    }

    public class UtilityBillDBContext : DbContext
    {
        public DbSet<UtilityBillModel> UtilityBills { get; set; }
    }
}