package org.elsys.netprog.rest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Random;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class client {
	public static void main(String[] args) throws Exception {
		 
		  String urlString = "http://localhost:8080/jersey-rest-homework/guess/get";
		  
		  URL url = new URL(urlString);
		  HttpURLConnection con = (HttpURLConnection) url.openConnection();
		 
		  con.setRequestMethod("GET");
		 
		  int responseCode = con.getResponseCode();
		  System.out.println("Sending get request : "+ url);
		  System.out.println("Response code : "+ responseCode);
		  BufferedReader in = new BufferedReader(
		          new InputStreamReader(con.getInputStream()));
		  String output;
		  StringBuffer response = new StringBuffer();
		 
		  while ((output = in.readLine()) != null) {
		   response.append(output);
		  }
		  in.close();
		  JSONParser parser = new JSONParser();
	      JSONObject obj = (JSONObject)parser.parse(response.toString());
	      long lenght = (Long) obj.get("lenght");
	      String hash = (String) obj.get("hash");
	      String myhash = "";
		  byte[] array = new byte[(int)lenght]; 
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      while(!hash.equals(myhash))
	      {
	    	  new Random().nextBytes(array);
	  		  md.update(array);
	  		  myhash = DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
	  		  
	      }
	      
	      JSONObject objnew = new JSONObject();
	      objnew.put("myhash", myhash);
	      objnew.put("bytes",new String(Base64.getEncoder().encode(array)));
		  URL urlpost = new URL("http://localhost:8080/jersey-rest-homework/guess/post");
		  HttpURLConnection conpost = (HttpURLConnection) urlpost.openConnection();
		  conpost.setRequestMethod("POST");
		  conpost.setRequestProperty("Content-Type", "application/json");
	      conpost.setDoOutput(true);
	      DataOutputStream out = new DataOutputStream(conpost.getOutputStream());
	      out.writeBytes(objnew.toJSONString());
	      out.flush();
	      out.close();
	      System.out.println(conpost.getResponseCode());
		 
		 }
}
