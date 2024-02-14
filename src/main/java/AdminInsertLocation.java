public class AdminInsertLocation {
    private String wkt;

    private String fias;

    private String name;

    private String type;

    public AdminInsertLocation(String wkt, String fias, String name, String type) {
        this.wkt = wkt;
        this.fias = fias;
        this.name = name;
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String generateINSERT(int id, int status) {
        if (status == 1) {
            return "INSERT INTO public.location (id, fias, level, name, type, geo_data_id) VALUES " +
                    "(" + id + ", '" + fias + "', 1, '" + name + "', '" + type + "', " +
                    "(SELECT id FROM public.geo_data as gd WHERE gd.fias = " + "'" + fias + "'))";
        }
        else {
            if (!fias.equals("181600e8-9bf6-4e9c-a00c-dfbca990c0d9"))
                return " ,(" + id + ", '" + fias + "', 1, '" + name + "', '" + type + "', " +
                    "(SELECT id FROM public.geo_data as gd WHERE gd.fias = " + "'" + fias + "'))";
        }
        return "";
    }
}
