using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using BankApplication.Models;

namespace BankApplication.ExportFactory
{
    public class CSVExporter : IExporter
    {
        private StringWriter sw;

        public CSVExporter()
        {
            sw = new StringWriter();
        }

        public void CreateLogExporter(List<LoggerModel> logger)
        {
            foreach (LoggerModel log in logger)
            {
                sw.WriteLine(string.Format("\"{0}\",\"{1}\"",

                log.transactionLog.ToString(),
                log.dateLog.ToString()
                 ));
            }
        }

        public StringWriter ReturnFile()
        {
            return sw;
        }
    }
}