using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace WebApplication3.Models
{
    public class EventModel
    {
        [Key]
        public int EventID { get; set; }

        [StringLength(60, MinimumLength = 3)]        
        public string EventTitle { get; set; }
        
        
        [Required]
        public string EventDirector { get; set; }
        
        [Required]
        public string EventDistribution { get; set; }
        
        [Required]
        [Range(1, 5000)]
        public int EventTickets { get; set; }

        [Display(Name = "Release Date")]
        [DataType(DataType.Date)]
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
        public DateTime EventReleaseDate { get; set; }
    }

    public class EventDBContext : DbContext
    {
        public DbSet<EventModel> Events { get; set; }
    }
}

