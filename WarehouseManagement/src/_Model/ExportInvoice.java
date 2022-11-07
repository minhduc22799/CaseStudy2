package _Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class ExportInvoice extends Invoice implements Serializable {
    private static int INDEX = 0;
    private long id;

    private ArrayList<DetailedInvoice> detailedExport = new ArrayList<>();

    public ExportInvoice() {
    }

    public ExportInvoice( LocalDate date, ArrayList<DetailedInvoice> detailedExport) {
        super("Export Invoice", date);
        this.id = Long.valueOf(++INDEX);
        this.detailedExport = detailedExport;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<DetailedInvoice> getDetailedExport() {
        return detailedExport;
    }

    public void setDetailedExport(ArrayList<DetailedInvoice> detailedExport) {
        this.detailedExport = detailedExport;
    }
}
