package DomainModel.Export;

/**
 * Created by Alex PC on 14/05/2016.
 */
public class ExportFactoryImpl implements  ExportFactory {

    public enum  Type {CSV, JSON};

    public Exporter createExporterObject (ExportFactoryImpl.Type  type) {
        switch (type) {
            case CSV :
                return new CsvExporter();
            case JSON:
                return new JsonExporter();
            default:
                throw new RuntimeException("Unsupported object type");
        }
    }
}
