package com.farmeasy.helper;
import com.farmeasy.dao.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.farmeasy.helper.ConnectionProvider;

public class Helper {

	public static String get10Words(String desc) {
		
		String[] strs = desc.split(" ");
	
		if(strs.length>10) {
			String res = "";
			for(int i=0;i<10;i++) {
				res = res + strs[i]+" ";
			}
			return (res+"...");
		}else {
			return (desc+"...");
		}
				
	}
	
}
