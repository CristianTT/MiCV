package dad.javafx.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import dad.javafx.models.Nacionalidad;

public class LectorCSV {

	public static final String SEPARATOR = "\n";
	public static final String NACIONALIDADES_PATH = "src/main/resources/csv/nacionalidades.csv";
	public static final String PAISES_PATH = "src/main/resources/csv/paises.csv";

	public static ArrayList<Nacionalidad> lectorNacionalidades() {
		ArrayList<Nacionalidad> nacionalidades = new ArrayList<Nacionalidad>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(NACIONALIDADES_PATH));
			String line = br.readLine();
			while (null != line) {
				nacionalidades.add(new Nacionalidad(line.split(SEPARATOR)[0]));
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return nacionalidades;
	}

	public static ArrayList<String> lectorPaises() {
		ArrayList<String> paises = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(PAISES_PATH));
			String line = br.readLine();
			while (null != line) {
				paises.add(line.split(SEPARATOR)[0]);
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return paises;
	}
	
}
