using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using BankApplication.Models;

namespace BankApplication.Controllers
{
    [Authorize(Roles = "User")]
    public class ClientController : Controller
    {
        private ClientDBContext db = new ClientDBContext();
        private LoggerDBContext db1 = new LoggerDBContext();

        // GET: /Client/
        public ActionResult Index()
        {
            return View(db.Clients.ToList());
        }

        // GET: /Client/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ClientModel clientmodel = db.Clients.Find(id);
            if (clientmodel == null)
            {
                return HttpNotFound();
            }
            return View(clientmodel);
        }

        // GET: /Client/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: /Client/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include="ClientID,ClientName,CNP,address")] ClientModel clientmodel)
        {
            if (ModelState.IsValid)
            {
                db.Clients.Add(clientmodel);
                db.SaveChanges();
                String currentUser = User.Identity.Name;
                db1.Loggers.Add(
                    new  LoggerModel{userNameLog = currentUser, 
                        dateLog = DateTime.Now, 
                        transactionLog = "Create client: " + clientmodel.ClientName.ToString() + "\n" }
                    );
                db1.SaveChanges();
                return RedirectToAction("Index");

            }

            return View(clientmodel);
        }

        // GET: /Client/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ClientModel clientmodel = db.Clients.Find(id);
            if (clientmodel == null)
            {
                return HttpNotFound();
            }
            return View(clientmodel);
        }

        // POST: /Client/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include="ClientID,ClientName,CNP,address")] ClientModel clientmodel)
        {
            if (ModelState.IsValid)
            {
                db.Entry(clientmodel).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(clientmodel);
        }

        // GET: /Client/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ClientModel clientmodel = db.Clients.Find(id);
            if (clientmodel == null)
            {
                return HttpNotFound();
            }
            return View(clientmodel);
        }

        // POST: /Client/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            ClientModel clientmodel = db.Clients.Find(id);
            db.Clients.Remove(clientmodel);
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
