package com.aem.learning.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSON2CSV {

	public static void main(String[] args) throws IOException {
 
		String fileInput = StringUtils.EMPTY;
		BufferedReader br = new BufferedReader(new FileReader("E:\\example_2.json"));
		
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			fileInput = sb.toString();
			System.out.println("File Result in String Format :"+ fileInput);
			
			JSONObject jsonObject = new JSONObject(fileInput);
			System.out.println("Json Objects :"+ jsonObject);
			
			File file=new File("E:\\example_2.csv");
			
			JSONArray keys = jsonObject.names();
			
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < keys.length (); ++i) {

				   String key = keys.getString (i); // key's of Json Objects
				   if(jsonObject.getJSONArray(key) instanceof JSONArray){
				   JSONArray  jsonArray = jsonObject.getJSONArray(key);
				   System.out.println(" value :"+jsonArray);
				   }else{
				   String value = jsonObject.getString (key); // value's of Json Objects 
				   System.out.println(" value :"+value);
				   }
				  // JSONArray innerJsonArray =  new JSONArray(value);
				   
				   /*for (int innterCount = 0, size = innerJsonArray.length(); innterCount < size; innterCount++)
				    {
					  String arrayValue = innerJsonArray.toString(innterCount);
					  
					  System.out.println("All array Value :"+arrayValue);
				    }*/
				   
				   System.out.println(" Key :"+key);
				   
				}
			
			//JSONArray jsonArray = jsonObject.getJSONArray("keywords");
			//System.out.println("Json Array :"+ jsonArray );
            
			//String csv = CDL.rowToString(jsonArray);
            //System.out.println("Final String :"+csv);
            
            //FileUtils.writeStringToFile(file, csv);
			
		} catch (IOException | JSONException e) {
         System.out.println("Error in Reading the file :"+e.getMessage());
		}finally {
			br.close();
			System.out.println("BufferReader successfully get closed ");
		}
	}

}
