using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace WebApplication3.Models
{
    public class TicketModel
    {

        [Key]
        public int TicketID { get; set; }
       
        [Required]
        [Display(Name = "Event title")]
        public string EventTitle { get; set; }
        
        [Required]
        [Display(Name = "Row number")]
        public int TicketRow { get; set; }
       
        [Required]
        [Display(Name = "Column number")]
        public int TicketColumn { get; set; }
    }

    public class TicketDBContext : DbContext
    {
        public DbSet<TicketModel> Tickets { get; set; }
    }

}