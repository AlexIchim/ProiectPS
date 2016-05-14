package DomainModel.Export;

/**
 * Created by Alex PC on 14/05/2016.
 */
public interface ExportFactory {
       Exporter createExporterObject(ExportFactoryImpl.Type type);
}
