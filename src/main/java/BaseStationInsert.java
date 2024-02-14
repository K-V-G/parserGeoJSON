public class BaseStationInsert {
    private String wkt;
    private String mestoustan;
    private String modifydate;
    private String standart;

    public BaseStationInsert(String wkt, String mestoustan, String modifydate, String standart) {
        this.wkt = wkt;
        this.mestoustan = mestoustan;
        this.modifydate = modifydate;
        this.standart = standart;
    }

    public String generateINSERT(int status) {
        if (status == 1) {
            return "INSERT INTO public.base_station (action_date, address, coverage_radius, point, " +
                    "prop_height, mobile_type_id, operator_id) VALUES ('" + modifydate + "', '" + mestoustan + "', null, " +
                    "ST_GeomFromText('SRID=4326;" + wkt + "'), null, " + getIDForStandart(standart) + ", 4)";
        }
        else {
            return " ,('" + modifydate + "', '" + mestoustan + "', null, " +
                    "ST_GeomFromText('SRID=4326;" + wkt + "'), null, " + getIDForStandart(standart) + ", 4)";
        }
    }

    public int getIDForStandart(String standart) {
        switch (standart) {
            case "2G" -> {
                return 1;
            }
            case "3G" -> {
                return 2;
            }
            case "4G" -> {
                return 3;
            }
            case "5G" -> {
                return 4;
            }
            case "Другие" -> {
                return 5;
            }
        }
        return 0;
    }

    public String getWkt() {
        return wkt;
    }

    public void setWkt(String wkt) {
        this.wkt = wkt;
    }

    public String getMestoustan() {
        return mestoustan;
    }

    public void setMestoustan(String mestoustan) {
        this.mestoustan = mestoustan;
    }

    public String getModifydate() {
        return modifydate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate;
    }

    public String getStandart() {
        return standart;
    }

    public void setStandart(String standart) {
        this.standart = standart;
    }
}
