namespace BankApplication.Migrations
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;
    using BankApplication.Models;

    internal sealed class Configuration : DbMigrationsConfiguration<BankApplication.Models.TransactionDBContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = false;
            ContextKey = "BankApplication.Models.TransactionDBContext";
        }

        protected override void Seed(BankApplication.Models.TransactionDBContext context)
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

            context.Transactions.AddOrUpdate(
                a => a.TransactionID,
                new TransactionModel {AccountFromID = 1 , AccountToID = 2, TransactionAmount =100, TransactionDate = DateTime.Now },
                new TransactionModel { AccountFromID = 2, AccountToID = 1, TransactionAmount = 200, TransactionDate = DateTime.Now }
                );
        }
    }
}
