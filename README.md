# DB_Transfer
This software migrate one database in SQLServer to Postgresql

The estructure for plain text file is this:


tablaOrigen:mascota columnasOrigen:id pk,name ; tablaDestino:mascota columnasDestino:id int,name String
tablaOrigen:mascota2 columnasOrigen:id pk,name,age ; tablaDestino:mascota2 columnasDestino:id int,name String,age int
tablaOrigen:mascota3 columnasOrigen:id pk,name,name2,name3,name4 ; tablaDestino:mascota3 columnasDestino:id int,name String,name2 String,name3 String,name4 String
tablaOrigen:mascota4 columnasOrigen:id pk ; tablaDestino:mascota4 columnasDestino:id int
