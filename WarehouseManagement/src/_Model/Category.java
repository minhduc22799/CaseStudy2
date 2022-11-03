package _Model;

import java.io.Serializable;

public class Category implements Serializable {
    private static int INDEX = 0;
    private long id;
    private String name;

    public Category() {
    }

    public Category( String name) {
        this.id = Long.valueOf(++INDEX);
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "" + id +
                "   " + name ;
    }


}
