using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BankApplication.Models;

namespace BankApplication.ExportFactory
{
    public interface IExporter
    {
        void CreateLogExporter(List<LoggerModel> log);
        StringWriter ReturnFile();
    }
}
