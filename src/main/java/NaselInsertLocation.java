import java.util.Objects;

public class NaselInsertLocation {
    private String wkt;

    private String fias;

    private String name;

    private String rayon;

    private String population;

    public NaselInsertLocation(String wkt, String fias, String name, String rayon, String population) {
        this.wkt = wkt;
        this.fias = fias;
        this.name = name;
        this.rayon = rayon;
        this.population = population;
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

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String generateINSERT(int id, int status) {
        if (status == 1) {
            return "INSERT INTO public.location (id, fias, level, name, type, population, geo_data_id, parent_id) VALUES " +
                    "(" + id + ", '" + fias + "', 2, '" +  name + "', 'г', " + population + ", " +
                    "(SELECT id FROM public.geo_data as gd WHERE gd.fias = " + "'" + fias + "'), (SELECT id FROM public.location as l WHERE l.name = '" + rayon + "'))";
        }
        else {
            if (fias != null && !fias.equals("null")) {
                if (fias.contains("\t")) {
                    fias = fias.replace("\t", "");
                }
                return " ,(" + id + ", '" + fias + "', 2, '" +  name + "', 'г', " + population + ", " +
                        "NULLIF((SELECT id FROM public.geo_data as gd WHERE gd.fias = " + "'" + fias + "'), null), NULLIF((SELECT id FROM public.location as l WHERE l.name = '" + rayon + "'), null))";
            }
            else {
                return "";
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NaselInsertLocation that = (NaselInsertLocation) o;
        return Objects.equals(wkt, that.wkt) && Objects.equals(fias, that.fias) && Objects.equals(name, that.name) && Objects.equals(rayon, that.rayon) && Objects.equals(population, that.population);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fias);
    }
}
