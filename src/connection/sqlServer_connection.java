package connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.table;

public class sqlServer_connection {
	 /*public static void connectDatabase() {
         Connection conn = null;
       try {
           String url = "jdbc:sqlserver://localhost:1433; databaseName=original; user=sa; password=Camilonfs1236;";
           conn = DriverManager.getConnection(url);
           if (conn != null) {
               DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
               System.out.println("Driver name: " + dm.getDriverName());
               System.out.println("Driver version: " + dm.getDriverVersion());
               System.out.println("Product name: " + dm.getDatabaseProductName());
               System.out.println("Product version: " + dm.getDatabaseProductVersion());
           }

       } catch (SQLException ex) {
           ex.printStackTrace();
       } finally {
           try {
               if (conn != null && !conn.isClosed()) {
                   conn.close();
               }
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
       }
   }*/
	

	 public static ArrayList read(table tables, int flag) {
			ArrayList<String> lines = new ArrayList();
			String url = "jdbc:sqlserver://localhost:1433; databaseName=original; user=sa; password=Camilonfs1236;";
			table table = tables;
			String column = "";
			for (int i = 0 ; i<=table.getColumns().length-1; i ++ ) {
				
				if (i ==0) {
					column= table.getColumns()[i];
				}else {
					column= column+","+table.getColumns()[i];
				}
			}
			String query ="";
			if(flag == 0) {
				query = "SELECT "+ column +" FROM "+table.getName();
			}else {
				query = "SELECT "+ column +" FROM "+table.getName()+" WHERE "+table.getPrimary()+" in (select MAX ("+table.getPrimary()+")"+" FROM "+table.getName()+")";				
			}
				
			
			try(Connection con = DriverManager.getConnection(url);
					PreparedStatement pst = con.prepareStatement(query);
					ResultSet rs = pst.executeQuery()) {
				String result = null;
				int count = 0;
				while(rs.next()) {
					count = count +1;
					for (int i = 1 ; i<=table.getColumns().length; i ++ ) {
						result = rs.getString(i);
						if (i ==1) {
							column= rs.getString(i);
						}else {
							column= column+","+rs.getString(i);;
						}
					}
					lines.add(column);				
				}
				if(flag == 0) {
					System.out.print("Se han consultado "+count+" lineas de la tabla "+table.getName()+"\n");
				}
				
							
			}catch(SQLException ex) {
				System.out.print(ex);			
			}		
			return lines;		
		}
	
}
