using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using BankApplication.ExportFactory;
using BankApplication.Models;

namespace BankApplication.Controllers
{
    [Authorize(Roles = "Admin")]
    public class LoggerController : Controller
    {
        private LoggerDBContext db = new LoggerDBContext();

        // GET: /Logger/
        public ActionResult Index()
        {
            return View(db.Loggers.ToList());
        }

        //ExportCsv for a specific user
        public ActionResult ExportCSV(string userName)
        {
            List<LoggerModel> lst = new List<LoggerModel>();


            foreach (LoggerModel log in db.Loggers) {
                if (log.userNameLog.Equals(userName)) {
                    lst.Add(log);
                }
            }
      
            ExporterFactory eF = new ExporterFactory();
            IExporter iE = eF.Export(ExporterFactory.ExportTypes.CSV, lst);

            var bytes = System.Text.Encoding.UTF8.GetBytes(iE.ReturnFile().ToString());
            return File(bytes, "text/csv", "Log.csv");
        }

        //Export JSON for a specific user
        public ActionResult ExportJSON(string userName)
        {
            List<LoggerModel> lst = new List<LoggerModel>();


            foreach (LoggerModel log in db.Loggers)
            {
                if (log.userNameLog.Equals(userName))
                {
                    lst.Add(log);
                }
            }

            ExporterFactory eF = new ExporterFactory();
            IExporter iE = eF.Export(ExporterFactory.ExportTypes.JSON, lst);

            var bytes = System.Text.Encoding.UTF8.GetBytes(iE.ReturnFile().ToString());
            return File(bytes, "text/json", "Log.json");
        }
        // GET: /Logger/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            LoggerModel loggermodel = db.Loggers.Find(id);
            if (loggermodel == null)
            {
                return HttpNotFound();
            }
            return RedirectToAction("ExportJSON", new {userName = loggermodel.userNameLog});
        }

        // GET: /Logger/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: /Logger/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include="LogID,userNameLog,transactionLog,dateLog")] LoggerModel loggermodel)
        {
            if (ModelState.IsValid)
            {
                db.Loggers.Add(loggermodel);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(loggermodel);
        }

        // GET: /Logger/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            LoggerModel loggermodel = db.Loggers.Find(id);
            if (loggermodel == null)
            {
                return HttpNotFound();
            }
            return RedirectToAction("ExportCSV", new { userName = loggermodel.userNameLog });
        }

        // POST: /Logger/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include="LogID,userNameLog,transactionLog,dateLog")] LoggerModel loggermodel)
        {
            if (ModelState.IsValid)
            {
                db.Entry(loggermodel).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(loggermodel);
        }

        // GET: /Logger/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            LoggerModel loggermodel = db.Loggers.Find(id);
            if (loggermodel == null)
            {
                return HttpNotFound();
            }
            return View(loggermodel);
        }

        // POST: /Logger/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            LoggerModel loggermodel = db.Loggers.Find(id);
            db.Loggers.Remove(loggermodel);
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
