namespace WebApplication3.Migrations
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;
    using WebApplication3.Models;

    internal sealed class Configuration : DbMigrationsConfiguration<WebApplication3.Models.EventDBContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = false;
        }

        protected override void Seed(WebApplication3.Models.EventDBContext context)
        {
            //  This method will be called after migrating to the latest version.

            //  You can use the DbSet<T>.AddOrUpdate() helper extension method 
            //  to avoid creating duplicate seed data. E.g.
            //
            //    context.People.AddOrUpdate(
            //      p => p.FullName,
            //      new Person { FullName = "Andrew Peters" },
            //      new Person { FullName = "Brice Lambson" },
            //      new Person { FullName = "Rowan Miller" }
            //    );
            //

            context.Events.AddOrUpdate(i => i.EventTitle,
       new EventModel
       {
           EventTitle = "Event1",
           EventDirector = "Director1",
           EventReleaseDate = DateTime.Parse("2013-11-11"),
           EventDistribution = "Distribution1",
           EventTickets = 500
       },

        new EventModel
        {
            EventTitle = "Event2",
            EventDirector = "Director2",
            EventReleaseDate = DateTime.Parse("2013-11-11"),
            EventDistribution = "Distribution2",
            EventTickets = 500
        },

        new EventModel
        {
            EventTitle = "Event3",
            EventDirector = "Director3",
            EventReleaseDate = DateTime.Parse("2013-11-11"),
            EventDistribution = "Distribution3",
            EventTickets = 500
        },

      new EventModel
      {
          EventTitle = "Event4",
          EventDirector = "Director4",
          EventReleaseDate = DateTime.Parse("2013-11-11"),
          EventDistribution = "Distribution4",
          EventTickets = 500
      }
  );
        }
    }
}
