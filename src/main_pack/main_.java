package main_pack;

import java.util.ArrayList;

import connection.psql_connection;
import connection.sqlServer_connection;
import controler.plain_text;
import model.column;
import model.table;
import model.table_destini;

public class main_ {
	public static table Objtable = new table();
	
	public static void main(String[] args) {
		
		ArrayList<String> objects = new ArrayList();
		
		
		objects=plain_text.text("../myfile.txt"); //Carga los datos del archivo plano linea por linea
		
		
		copiar_nueva(objects,0);
		
		while(true) {
			objects=plain_text.text("../myfile.txt"); 
			try {
				Thread.sleep(3000);
				copiar_nueva(objects,0);
				System.out.print("...\n");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static void copiar_nueva(ArrayList<String> objects, int flag) {
		ArrayList<table> tables_origin  = new ArrayList();
		ArrayList<table_destini> tables_desti  = new ArrayList();
		ArrayList<String> lines = new ArrayList();
		tables_origin = divid_origin(objects);	
		tables_desti = divid_destination(objects);
		
		for (int i= 0; i<= tables_origin .size()-1; i ++) {
			lines = new ArrayList();
			lines = sqlServer_connection.read(tables_origin .get(i),flag);
			psql_connection.write(tables_desti.get(i),lines);
			
		}
		
		
	}
	private static ArrayList divid_origin(ArrayList objects) {//Divide los datos del archivo en nombre y columnas para consultar
		ArrayList<table> tables = new ArrayList();
		table Objtable = new table();
		for (int i =0; i<=objects.size()-2;i++) {
			Objtable = new table();			
			String[] origen = ((String) objects.get(i)).split(" ; ");
			
			//Busca la llave primaria marcada por PK en el archivo plano
			String[] partis = ((String) origen[0]).split(":");	
			String[] partis2 = ((String) partis[2]).split(",");
			String[] partis3 = ((String) partis2[0]).split(" ");		
			
			String[] parts = ((String) origen[0]).split("columnasOrigen");	
			
			String[] table = parts[0].split(":");	
			
			String[] parts2 = parts[1].split(":");				
			String[] columns = parts2[1].split(",");
			String[] columnsaux = columns[0].split(" ");
			columns[0] = columnsaux[0];
			Objtable.setName(table[1]);
			Objtable.setPrimary(partis3[0]);
			Objtable.setColumns(columns);
			
			
			tables.add(Objtable);
		}
		
		return tables;
	}
	
	private static ArrayList divid_destination(ArrayList objects) {//Divide los datos del archivo en nombre y columnas para insertar
		ArrayList<table_destini> tables = new ArrayList();
		ArrayList<column> columns = new ArrayList();
		ArrayList<Integer> numcolumn = new ArrayList();
		
		column col = new column();
		table_destini Objtable = new table_destini();
		
		for (int i =0; i<=objects.size()-2;i++) { 
			Objtable = new table_destini();
			columns = new ArrayList();
			
			String[] origen = ((String) objects.get(i)).split(" ; ");			
			String[] parts = ((String) origen[1]).split("columnasDestino");			
			String[] parts2 = ((String) parts[1]).split(":");	
			String[] parts3 = ((String) parts2[1]).split(",");
		
			String[] tabla = ((String) parts[0]).split(":");
			
			Objtable.setName(tabla[1]); //Guarda el nombre de cada tabla		
			
			for (int x =0; x <=parts3.length-1;x++) {				
				String[] parts40 =null;
				parts40 = ((String) parts3[x]).split(" ");
				col = new column(parts40[0],parts40[1]);
				columns.add(col);//Guarda el nombre de cada columna y el tipo de cada columna			
			}		
			Objtable.setColumns(columns);
			tables.add(Objtable);
			numcolumn.add(parts3.length);			
		}						
		return tables;
	}

}
