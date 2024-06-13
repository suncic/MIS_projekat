package crud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.Lekar;
import entity.Nabavka;

public class UslugaCrud {
	public static String[] citajFajl(String imef) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("fajlovi/" + imef));
		String linija = null;
		List<String> linije = new ArrayList<String>(); 
			
		while((linija = br.readLine()) != null) {
			linije.add(linija);
		}
			
		return linije.toArray(new String[linije.size()]);
		
	}
	
	public static void zakazi(String u, String l, String t) {
		BufferedWriter bw = null;
		 try {
			bw = new BufferedWriter(new FileWriter("fajlovi/pregledi"));
	     	bw.write(l + ", " + t + " -> usluga: " + u);
		 } catch (IOException e) {
		    e.printStackTrace();
		 }finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		 }
	}
	
	public static void naruci(Nabavka n) {
		BufferedWriter bw = null;
		 try {
			bw = new BufferedWriter(new FileWriter("fajlovi/narudzbina"));
	     	bw.write(n.toString());
		 } catch (IOException e) {
		    e.printStackTrace();
		 }finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		 }
	}
	
	public static boolean logIn(String user, char[] passw, String uloga) throws IOException {
		String pasw = "";
		for(char c : passw) {
			pasw += c;
		}
		
		if(uloga.equals("pacijent")) {
			String[] fajlPacijenti = citajFajl("pacijenti.csv");
			
			for(int i = 0; i < fajlPacijenti.length; i++) {
				Pattern pat = Pattern.compile("(.+);(.+)");
				Matcher mat = pat.matcher(fajlPacijenti[i]);
				
				if(mat.find()) {
					String u = mat.group(1).trim();
					String p = mat.group(2).trim();
					
					if(u.equals(user) && p.equals(pasw)) {
						return true;
					}
				}
			}
		}else {
			String[] fajlPacijenti = citajFajl("medsestre.csv");
			
			for(int i = 0; i < fajlPacijenti.length; i++) {
				Pattern pat = Pattern.compile("(.+);(.+)");
				Matcher mat = pat.matcher(fajlPacijenti[i]);
				
				if(mat.find()) {
					String u = mat.group(1).trim();
					String p = mat.group(2).trim();
					
					if(u.equals(user) && p.equals(pasw)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
