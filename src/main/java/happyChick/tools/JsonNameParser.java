package happyChick.tools;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonNameParser {
	
	public List<String> nomPoules = new ArrayList();
	public static List<String> nomCoqs = new ArrayList();
	
	public void JsonNameParser() {
		
		try {
			/*URL url = new URL("https://opendata.paris.fr/api/records/1.0/search/?dataset=liste_des_prenoms&q=&facet=sexe&facet=annee&facet=prenoms");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			if(conn.getResponseCode() == 200) {
				Scanner scan = new Scanner(url.openStream());
				while(scan.hasNext()) {
					String temp = scan.nextLine();
                  	//parse json here
					JSONObject obj = new JSONObject(temp);
					JSONArray records = (JSONArray) obj.get("records");
					for (int i = 0 ; i < records.length() ; i++) {
						JSONObject fields = (JSONObject) records.getJSONObject(i).get("fields");
						System.out.println(fields.getString("sexe"));
					}
                }
            }*/
			FileSystem fs = FileSystems.getDefault();
			Path path = fs.getPath("src/main/resources/liste_des_prenoms.json");
			String tmp = Files.readString(path, StandardCharsets.US_ASCII);
			JSONArray obj = new JSONArray(tmp);
			for (int i = 0 ; i < obj.length() ; i++) {
				JSONObject fields = (JSONObject) obj.getJSONObject(i).get("fields");
				if (fields.getString("sexe").equals("M")) {
					nomCoqs.add(fields.getString("prenoms"));
				}
				else {
					nomPoules.add(fields.getString("prenoms"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String genererNomPoule() {
		Random r = new Random();
		return nomPoules.get(r.nextInt(nomPoules.size()));
	}
	
	public String genererNomCoq() {
		Random r = new Random();
		return nomCoqs.get(r.nextInt(nomCoqs.size()));
	}
}