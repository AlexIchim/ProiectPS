namespace WebApplication3.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class initial : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.EventModels",
                c => new
                    {
                        EventID = c.Int(nullable: false, identity: true),
                        EventTitle = c.String(maxLength: 60),
                        EventDirector = c.String(nullable: false),
                        EventDistribution = c.String(nullable: false),
                        EventTickets = c.Int(nullable: false),
                        EventReleaseDate = c.DateTime(nullable: false),
                    })
                .PrimaryKey(t => t.EventID);
            
        }
        
        public override void Down()
        {
            DropTable("dbo.EventModels");
        }
    }
}
