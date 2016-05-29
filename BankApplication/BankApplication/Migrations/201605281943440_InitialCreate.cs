namespace BankApplication.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class InitialCreate : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.TransactionModels",
                c => new
                    {
                        TransactionID = c.Int(nullable: false, identity: true),
                        AccountFromID = c.Int(nullable: false),
                        AccountToID = c.Int(nullable: false),
                        TransactionAmount = c.Int(nullable: false),
                        TransactionDate = c.DateTime(nullable: false),
                    })
                .PrimaryKey(t => t.TransactionID);
            
        }
        
        public override void Down()
        {
            DropTable("dbo.TransactionModels");
        }
    }
}
