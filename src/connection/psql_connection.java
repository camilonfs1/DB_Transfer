package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.column;
import model.table;
import model.table_destini;


public class psql_connection {

	public static void write(table_destini tables_desti,ArrayList<String> lines) {
		String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "123456";		
        
		ArrayList<String> data= new ArrayList();
		
		for (int x= 0; x<= lines.size()-1; x ++) {
			String[] parts = ((String) lines.get(x)).split(",");
			for(int i= 0; i<= parts.length-1; i ++) {
				data.add(parts[i]);
			}
		}	
		
		table_destini Objtable = new table_destini();
		column col = new column();		
		Objtable = tables_desti;
		String interroga = "";
		
		for(int x=0;x<=Objtable.getColumns().size()-1;x++) {
			if(x==Objtable.getColumns().size()-1) {
				interroga = interroga +"?";
			}else {
				interroga = interroga +"?,";
			}
		}
		
		String query = "INSERT INTO "+ Objtable.getName() +"VALUES ("+ interroga+") ON CONFLICT DO NOTHING";
		
		int aux = 1;
		int aux_data = 0;
		
		try(Connection con = DriverManager.getConnection(url, user, password);			
			PreparedStatement pst = con.prepareStatement(query);) {		
			for(int i=0;i<=data.size()/Objtable.getColumns().size()-1;i++) {
				aux = 1;
				for(int x=0;x<=Objtable.getColumns().size()-1;x++) {
					col= Objtable.getColumns().get(x);						 
					if(col.getType().equals("int")) {
						pst.setInt(aux, Integer.parseInt(data.get(aux_data)));
						aux = aux+1;
					 }
					 if(col.getType().equals("String")) {
						 pst.setString(aux, data.get(aux_data));				 
						 aux = aux+1;
					 } 
					 if (x==Objtable.getColumns().size()-1) {	
						 pst.executeUpdate();
					 }
					 aux_data=aux_data+1;										
				 }
			}			
		}catch(SQLException ex) {
			System.out.print(ex);			
		}		
	}
}
