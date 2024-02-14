import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("/home/developer2/parserGeoJSON/nasel.json");

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> geoJsonObject = objectMapper.readValue(file, Map.class);

        List<Map<String, Object>> features = (List<Map<String, Object>>) geoJsonObject.get("features");
        List<NaselInsertLocation> adminInserts = new ArrayList<>();

        for (Map<String, Object> feature : features) {
            Map<String, Object> geometry = (Map<String, Object>) feature.get("properties");
            String wkt = (String) geometry.get("wkt");
            /*String[] wktStrings = ((String) geometry.get("wkt")).split(" ");
            String wkt = "POINT" + wktStrings[1].replace("((", "(") + " " + wktStrings[2].replace("))", ")");
            System.out.println(wkt);*/
            String name = (String) geometry.get("name");
            String rayon = (String) geometry.get("rayon");
            String population = (String) geometry.get("population");

            String fias = (String) geometry.get("aoguid");

            /*String type = (String) geometry.get("type");
            switch (type){
                case "Городской округ" -> type = "го";
                case "Административный район" -> type = "р-н";
                default -> type = "Другие";
            }*/
            adminInserts.add(new NaselInsertLocation(wkt, fias, name, rayon, population));
        }
        String init = adminInserts.get(0).generateINSERT(21, 1);
        
        adminInserts.remove(0);
        List<NaselInsertLocation> listNew = Set.copyOf(adminInserts).stream().toList();
        int id = 22;
        for (int i = 1; i < listNew.size(); i++) {
            init = init.concat(listNew.get(i).generateINSERT(id, 0));
            id++;
            System.out.println(listNew.get(i).getFias());
        }
        File file1 = new File("./result.txt");
        file1.createNewFile();
        FileWriter fileWriter = new FileWriter(file1, false);
        fileWriter.write(init);
        fileWriter.close();
    }
}

