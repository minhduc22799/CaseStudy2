package _Model;

import java.io.Serializable;
import java.util.ArrayList;

public class ArrayDetailImport implements Serializable {
    private ArrayList<DetailedInvoice> detailedInvoicesImport = new ArrayList<>();

    public ArrayDetailImport() {
    }

    public ArrayDetailImport(ArrayList<DetailedInvoice> detailedInvoicesImport) {
        this.detailedInvoicesImport = detailedInvoicesImport;
    }

    public ArrayList<DetailedInvoice> getDetailedInvoicesImport() {
        return detailedInvoicesImport;
    }

    public void setDetailedInvoicesImport(ArrayList<DetailedInvoice> detailedInvoicesImport) {
        this.detailedInvoicesImport = detailedInvoicesImport;
    }
}

