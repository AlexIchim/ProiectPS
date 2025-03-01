﻿using System;
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
    public class BankAccountController : Controller
    {
        private AccountDBContext db = new AccountDBContext();

        // GET: /BankAccount/
        public ActionResult Index()
        {
            return View(db.Accounts.ToList());
        }

        // GET: /BankAccount/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            BankAccountModel bankaccountmodel = db.Accounts.Find(id);
            if (bankaccountmodel == null)
            {
                return HttpNotFound();
            }
            return View(bankaccountmodel);
        }

        // GET: /BankAccount/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: /BankAccount/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include="AccountID,AccountClientID,AccountBalance,AccountType,AccountCreationDate")] BankAccountModel bankaccountmodel)
        {
            if (ModelState.IsValid)
            {
                db.Accounts.Add(bankaccountmodel);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(bankaccountmodel);
        }

        // GET: /BankAccount/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            BankAccountModel bankaccountmodel = db.Accounts.Find(id);
            if (bankaccountmodel == null)
            {
                return HttpNotFound();
            }
            return View(bankaccountmodel);
        }

        // POST: /BankAccount/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include="AccountID,AccountClientID,AccountBalance,AccountType,AccountCreationDate")] BankAccountModel bankaccountmodel)
        {
            if (ModelState.IsValid)
            {
                db.Entry(bankaccountmodel).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(bankaccountmodel);
        }

        // GET: /BankAccount/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            BankAccountModel bankaccountmodel = db.Accounts.Find(id);
            if (bankaccountmodel == null)
            {
                return HttpNotFound();
            }
            return View(bankaccountmodel);
        }

        // POST: /BankAccount/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            BankAccountModel bankaccountmodel = db.Accounts.Find(id);
            db.Accounts.Remove(bankaccountmodel);
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
