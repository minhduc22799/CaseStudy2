package _Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class ImportInvoice extends Invoice implements Serializable {
    private static int INDEX = 0;
    private long id;

    private ArrayList<DetailedInvoice> detailedInvoices = new ArrayList<>();

    public ImportInvoice() {
    }

    public ImportInvoice(LocalDate date, ArrayList<DetailedInvoice> detailedInvoices) {
        super("Import Invoice" , date);
        this.id = Long.valueOf(++INDEX);
        this.detailedInvoices = detailedInvoices;
    }



    public ArrayList<DetailedInvoice> getDetailedInvoices() {
        return detailedInvoices;
    }

    public void setDetailedInvoices(ArrayList<DetailedInvoice> detailedInvoices) {
        this.detailedInvoices = detailedInvoices;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
