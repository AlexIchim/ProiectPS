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
    public class TransactionController : Controller
    {
        private TransactionDBContext db = new TransactionDBContext();
        private AccountDBContext db2 = new AccountDBContext();
        private LoggerDBContext db3 = new LoggerDBContext();


        // GET: /Transaction/
        public ActionResult Index()
        {

            return View(db.Transactions.ToList());
        }

        // GET: /Transaction/Error1
        public ActionResult Error1()
        {
            return View();
        }

        // GET: /Transaction/Error2
        public ActionResult Error2()
        {
            return View();
        }

        // GET: /Transaction/Error
        public ActionResult Error()
        {
            return View();
        }

        // GET: /Transaction/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            TransactionModel transactionmodel = db.Transactions.Find(id);
            if (transactionmodel == null)
            {
                return HttpNotFound();
            }
            return View(transactionmodel);
        }

        // GET: /Transaction/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: /Transaction/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "TransactionID,AccountFromID,AccountToID,TransactionAmount,TransactionDate")] TransactionModel transactionmodel)
        {

            if (ModelState.IsValid)
            {


                var clientID1 = from a in db2.Accounts
                                where a.AccountID.Equals(transactionmodel.AccountFromID)
                                select a.AccountClientID;

                var clientID2 = from a in db2.Accounts
                                where a.AccountID.Equals(transactionmodel.AccountToID)
                                select a.AccountClientID;


                if (clientID1 != null && clientID2 != null && transactionmodel.AccountFromID != transactionmodel.AccountToID)
                {
                    var AccBalance1 = from a in db2.Accounts
                                      where a.AccountID.Equals(transactionmodel.AccountFromID)
                                      select a.AccountBalance;

                    var AccBalance2 = from a in db2.Accounts
                                      where a.AccountID.Equals(transactionmodel.AccountToID)
                                      select a.AccountBalance;


                    if (AccBalance1.FirstOrDefault() - transactionmodel.TransactionAmount >= 0)
                    {
                        var accountToUpdateFrom = db2.Accounts.Where(o => o.AccountID == transactionmodel.AccountFromID);
                        var accountToUpdateTo = db2.Accounts.Where(o => o.AccountID == transactionmodel.AccountToID);

                        // update LastName for all Persons in personsToUpdate
                        foreach (BankAccountModel p in accountToUpdateFrom)
                        {
                            p.AccountBalance = AccBalance1.FirstOrDefault() - transactionmodel.TransactionAmount;
                        }

                        foreach (BankAccountModel p in accountToUpdateTo)
                        {
                            p.AccountBalance = AccBalance2.FirstOrDefault() + transactionmodel.TransactionAmount;
                        }
                        db.Transactions.Add(transactionmodel);
                        db.SaveChanges();
                        db2.SaveChanges();
                        String currentUser = User.Identity.Name;
                        db3.Loggers.Add(
                        new LoggerModel {   userNameLog = currentUser, 
                                            dateLog = DateTime.Now, 
                                            transactionLog = "Transfer: " + transactionmodel.AccountFromID.ToString() + " to " + transactionmodel.AccountToID.ToString() + " amount: " + transactionmodel.TransactionAmount.ToString() + "\n" }
                            );
                        db3.SaveChanges();
                        return RedirectToAction("Index");
                    }
                    else
                    {
                        return RedirectToAction("Error1");
                    }
                }
                else
                {
                    return RedirectToAction("Error2");
                }
            }
            return RedirectToAction("Error");
        }

        // GET: /Transaction/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            TransactionModel transactionmodel = db.Transactions.Find(id);
            if (transactionmodel == null)
            {
                return HttpNotFound();
            }
            return View(transactionmodel);
        }

        // POST: /Transaction/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include="TransactionID,AccountFromID,AccountToID,TransactionAmount,TransactionDate")] TransactionModel transactionmodel)
        {
            if (ModelState.IsValid)
            {
                db.Entry(transactionmodel).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(transactionmodel);
        }

        // GET: /Transaction/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            TransactionModel transactionmodel = db.Transactions.Find(id);
            if (transactionmodel == null)
            {
                return HttpNotFound();
            }
            return View(transactionmodel);
        }

        // POST: /Transaction/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            TransactionModel transactionmodel = db.Transactions.Find(id);
            db.Transactions.Remove(transactionmodel);
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
