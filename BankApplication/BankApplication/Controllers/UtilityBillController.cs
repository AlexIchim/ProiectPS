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
    public class UtilityBillController : Controller
    {
        private UtilityBillDBContext db = new UtilityBillDBContext();
        private LoggerDBContext db1 = new LoggerDBContext();
        // GET: /UtilityBill/
        public ActionResult Index()
        {
            return View(db.UtilityBills.ToList());
        }

        // GET: /UtilityBill/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            UtilityBillModel utilitybillmodel = db.UtilityBills.Find(id);
            if (utilitybillmodel == null)
            {
                return HttpNotFound();
            }
            return View(utilitybillmodel);
        }

        // GET: /UtilityBill/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: /UtilityBill/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include="UtilityBillID,AccountToPayID,AmountToPay")] UtilityBillModel utilitybillmodel)
        {
            if (ModelState.IsValid)
            {
                db.UtilityBills.Add(utilitybillmodel);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(utilitybillmodel);
        }

        // GET: /UtilityBill/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            UtilityBillModel utilitybillmodel = db.UtilityBills.Find(id);
            if (utilitybillmodel == null)
            {
                return HttpNotFound();
            }
            return View(utilitybillmodel);
        }

        // POST: /UtilityBill/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include="UtilityBillID,AccountToPayID,AmountToPay")] UtilityBillModel utilitybillmodel)
        {
            if (ModelState.IsValid)
            {
                db.Entry(utilitybillmodel).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(utilitybillmodel);
        }

        //Account does not exist ERROR
        public ActionResult Error ()
        {
            return View();
        }

        //Insuficient funds error
        public ActionResult Error1()
        {
            return View();
        }

        // GET: /UtilityBill/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            UtilityBillModel utilitybillmodel = db.UtilityBills.Find(id);
            if (utilitybillmodel == null)
            {
                return HttpNotFound();
            }
            return View(utilitybillmodel);
        }

        // POST: /UtilityBill/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            AccountDBContext db2 = new AccountDBContext();
            UtilityBillModel utilitybillmodel = db.UtilityBills.Find(id);

            var accSum = from a in db2.Accounts
                      where a.AccountID.Equals(utilitybillmodel.AccountToPayID)
                      select a.AccountBalance;
            
            //Check if account exists
            if (accSum != null)
            {
                //Check if account sum is greater than the bill
                if (accSum.FirstOrDefault() >= utilitybillmodel.AmountToPay) //pay
                {
                    var accountToUpdate = db2.Accounts.Where(o => o.AccountID == utilitybillmodel.AccountToPayID);

                    // update LastName for all Persons in personsToUpdate
                    foreach (BankAccountModel p in accountToUpdate)
                    {
                        p.AccountBalance = accSum.FirstOrDefault() - utilitybillmodel.AmountToPay;
                    }
                    db.UtilityBills.Remove(utilitybillmodel);
                    db.SaveChanges();
                    db2.SaveChanges();
                    String currentUser = User.Identity.Name;
                    db1.Loggers.Add(
                        new LoggerModel { 
                            dateLog = DateTime.Now,
                            userNameLog = currentUser,
                            transactionLog = "Utility payed for " + utilitybillmodel.AccountToPayID + " for amount " + utilitybillmodel.AmountToPay
                        }
                        );
                    db1.SaveChanges();
                    return RedirectToAction("Index");
                }
                else
                {
                    return RedirectToAction("Error1");
                }
            }
            //If account does not exist
            else
            {
                return RedirectToAction("Error");
            }
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
