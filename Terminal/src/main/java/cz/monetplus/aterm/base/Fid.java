package cz.monetplus.aterm.base;

/**
 * Created by krajcovic on 11/5/15.
 */
public class Fid {

    private String fid;

    private String value;

    public Fid(String fid, String value) {
        this.fid = fid;
        this.value = value;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fid fid1 = (Fid) o;

        if (!fid.equals(fid1.fid)) return false;
        return value.equals(fid1.value);

    }

    @Override
    public int hashCode() {
        int result = fid.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Fid{" +
                "fid='" + fid + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
