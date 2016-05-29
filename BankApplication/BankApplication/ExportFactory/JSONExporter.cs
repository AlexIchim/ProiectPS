using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using BankApplication.Models;
using Newtonsoft.Json;

namespace BankApplication.ExportFactory
{
    public class JSONExporter : IExporter
    {
        private StringWriter sw;

        public JSONExporter()
        {
            sw = new StringWriter();
        }

        public void CreateLogExporter(List<LoggerModel> loggers)
        {
            JsonWriter jw = new JsonTextWriter(sw);
            jw.Formatting = Formatting.Indented;
            foreach (LoggerModel log in loggers)
            {
                jw.WriteStartObject();
                jw.WritePropertyName("Action");
                jw.WriteValue(log.transactionLog);
                jw.WritePropertyName("Date");
                jw.WriteValue(log.dateLog);
                jw.WriteEndObject();
            }
        }

        public StringWriter ReturnFile()
        {
            return sw;
        }
    }
}