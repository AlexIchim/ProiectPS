using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using WebApplication3.Models;

namespace WebApplication3.Controllers
{
    
    [Authorize(Roles = "User")]
    public class TicketController : Controller
    {
        private EventDBContext dbEv = new EventDBContext();
        private TicketDBContext db = new TicketDBContext();

        // GET: /Ticket/
        public ActionResult Index(string searchString)
        {
            var tickets = from m in db.Tickets
                          select m;

            if (!String.IsNullOrEmpty(searchString))
            {
                tickets = tickets.Where(s => s.EventTitle.Contains(searchString));
            }

            return View(tickets);
        }

        // GET: /Ticket/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            TicketModel ticketmodel = db.Tickets.Find(id);
            if (ticketmodel == null)
            {
                return HttpNotFound();
            }
            return View(ticketmodel);
        }

        // GET: /Ticket/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: /Ticket/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include="TicketID,EventTitle,TicketRow,TicketColumn")] TicketModel ticketmodel)
        {
            if (ModelState.IsValid &&
                !(
                db.Tickets.Any(
                    t => t.EventTitle.Equals(ticketmodel.EventTitle) &&
                    t.TicketRow.Equals(ticketmodel.TicketRow) &&
                    t.TicketColumn.Equals(ticketmodel.TicketColumn))
                )
                &&
                dbEv.Events.Any(e => e.EventTitle.Equals(ticketmodel.EventTitle))
                )
           
            {

                var q = from x in dbEv.Events
                        where x.EventTitle.Equals(ticketmodel.EventTitle)
                        select x.EventTickets;

                int ticketsNr = q.FirstOrDefault();
                
                if (db.Tickets.Count(t => t.EventTitle.Equals(ticketmodel.EventTitle)) < ticketsNr) 
                {
                    db.Tickets.Add(ticketmodel);
                    db.SaveChanges();
                    return RedirectToAction("Index");
                }
                //db.Tickets.Add(ticketmodel);
                //db.SaveChanges();
                //return RedirectToAction("Index");
            }

            //return View(ticketmodel);
            return RedirectToAction("Index");
        }

        // GET: /Ticket/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            TicketModel ticketmodel = db.Tickets.Find(id);
            if (ticketmodel == null)
            {
                return HttpNotFound();
            }
            return View(ticketmodel);
        }

        // POST: /Ticket/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include="TicketID,EventTitle,TicketRow,TicketColumn")] TicketModel ticketmodel)
        {
            if (ModelState.IsValid)
            {
                db.Entry(ticketmodel).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(ticketmodel);
        }

        // GET: /Ticket/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            TicketModel ticketmodel = db.Tickets.Find(id);
            if (ticketmodel == null)
            {
                return HttpNotFound();
            }
            return View(ticketmodel);
        }

        // POST: /Ticket/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            TicketModel ticketmodel = db.Tickets.Find(id);
            db.Tickets.Remove(ticketmodel);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
