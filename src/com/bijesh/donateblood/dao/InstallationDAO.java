package com.bijesh.donateblood.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bijesh.donateblood.TableConstants;
import com.bijesh.donateblood.database.DataStore;
import com.bijesh.donateblood.models.Installation;

public class InstallationDAO extends DataStore implements TableConstants{
	
	
	public void isUserAlreadyInstalled(){
		try{
		dbConnect();
		System.out.println("$$$$$$$$$ Connection Established $$$$$$$$$$");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
    public void insertDeviceInstallation(Installation installation){
        try{
        dbConnect();
    	String query ="INSERT INTO "+INSTALLATION_TABLE_NAME+" ("+INSTALLATION_COLUMN_UNIQUEID+
    			  ","+INSTALLATION_COLUMN_TIME+","+INSTALLATION_COLUMN_TIMEZONE+") Values (?,?,?)" ;
    	
		PreparedStatement statement = getPrepStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setString(1, installation.getUniqueId());
		statement.setString(2,installation.getTime());
		statement.setString(3,installation.getTimezone());
		
		dbExecutePrep();
		System.out.println("$$$$ Row inserted $$$$$$$");
        }catch (SQLException e){
        	e.printStackTrace();
        }
    }

}
