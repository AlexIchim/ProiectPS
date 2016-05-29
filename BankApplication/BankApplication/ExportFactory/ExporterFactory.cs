using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using BankApplication.Models;

namespace BankApplication.ExportFactory
{
    public class ExporterFactory
    {
        public enum ExportTypes { JSON, CSV };

        public IExporter Export(ExportTypes type, List<LoggerModel> log)
        {
            switch (type)
            {
                case ExportTypes.CSV:
                    CSVExporter csv = new CSVExporter();
                    csv.CreateLogExporter(log);
                    return csv;
                case ExportTypes.JSON:
                    JSONExporter jsn = new JSONExporter();
                    jsn.CreateLogExporter(log);
                    return jsn;
                default:
                    throw new ArgumentOutOfRangeException("type", type, null);
            }
            return null;
        }
    }
}