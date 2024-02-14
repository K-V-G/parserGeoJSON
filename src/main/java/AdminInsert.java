public class AdminInsert {
    private String wkt;

    private String fias;


    public AdminInsert(String wkt, String fias) {
        this.wkt = wkt;
        this.fias = fias;
    }

    public String getWkt() {
        return wkt;
    }

    public void setWkt(String wkt) {
        this.wkt = wkt;
    }

    public String getFias() {
        return fias;
    }

    public void setFias(String fias) {
        this.fias = fias;
    }

    public String generateINSERT(int id, int status) {
        if (status == 1) {
            return "INSERT INTO public.geo_data (id, border, fias) VALUES (" + id + ", ST_GeomFromText('SRID=4326;" + wkt + "'), '" + fias + "')";
        }
        else {
            return " ,(" + id + ", ST_GeomFromText('SRID=4326;" + wkt + "'), '" + fias + "')";
        }
    }
}
