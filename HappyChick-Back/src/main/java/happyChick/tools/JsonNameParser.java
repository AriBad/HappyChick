package happyChick.tools;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JsonNameParser {
	
	class NomNombre {
		private String nom;
		private int nb;
		
		NomNombre(String nom, int nb) {
			this.nom = nom;
			this.nb = nb;
		}
	}
	
	private List<NomNombre> nomPoules = new ArrayList();
	private List<NomNombre> nomCoqs = new ArrayList();
	
	//Ces deux variables permettront de générer plus souvent les noms les plus donnés en France, et moins souvent les noms les plus rares
	private int nomsMasculins = 0;
	private int nomsFeminins = 0;
	
	public JsonNameParser() {
		
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
			Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream("liste_des_prenoms.json"));
			String tmp = sc.nextLine();
			//getClass().getClassLoader().getResourceAsStream("liste_des_prenoms.json")
			//FileSystem fs = FileSystems.getDefault();
			//Path path = fs.getPath("src/main/resources/liste_des_prenoms.json");
			//String tmp = Files.readString(path, StandardCharsets.US_ASCII);
			JSONArray obj = new JSONArray(tmp);
			for (int i = 0 ; i < obj.length() ; i++) {
				JSONObject fields = (JSONObject) obj.getJSONObject(i).get("fields");
				if (fields.getString("sexe").equals("M")) {
					nomsMasculins+=fields.getInt("nombre");
					nomCoqs.add(new NomNombre(fields.getString("prenoms"), fields.getInt("nombre") ) );
				}
				else {
					nomsFeminins+=fields.getInt("nombre");
					nomPoules.add(new NomNombre(fields.getString("prenoms"), fields.getInt("nombre") ));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Pour ces deux fonctions, "proportionnel" dit si oui ou non on veut que les noms les plus courants aient plus de chance d'être
	//choisis, et inversement les noms les plus rares aient moins de chance d'être générés.
	public String genererNomPoule(boolean proportionnel) {
		Random r = new Random();
		if (!proportionnel) {
			return nomPoules.get(r.nextInt(nomPoules.size())).nom;
		} else {
			int tmp = r.nextInt(nomsFeminins);
			int cpt = 0;
			for (NomNombre current : nomPoules) {
				cpt += current.nb;
				if (cpt > tmp) {
					return current.nom;
				}
			}
			//ce cas n'arrive jamais, mais obligé pour compiler
			return genererNomPoule(false);
		}
	}
	
	public String genererNomCoq(boolean proportionnel) {
		Random r = new Random();
		if (!proportionnel) {
			return nomCoqs.get(r.nextInt(nomCoqs.size())).nom;
		} else {
			int tmp = r.nextInt(nomsMasculins);
			int cpt = 0;
			for (NomNombre current : nomCoqs) {
				cpt += current.nb;
				if (cpt > tmp) {
					return current.nom;
				}
			}
			//ce cas n'arrive jamais, mais obligé pour compiler
			return genererNomCoq(false);
		}
	}
}