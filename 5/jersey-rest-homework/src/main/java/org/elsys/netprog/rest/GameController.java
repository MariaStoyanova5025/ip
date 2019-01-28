package org.elsys.netprog.rest;

import java.net.URI;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

@Path("/guess")
public class GameController {
	private static String hash;
	private static int lenght = 3;
	private static byte[] array = new byte[lenght]; 
	@GET
	@Path("/get")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response getHash() throws NoSuchAlgorithmException {
		new Random().nextBytes(array);
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(array);
		hash = DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
		JSONObject obj = new JSONObject();
		obj.put("hash", hash);
		obj.put("lenght", lenght);
		return Response.ok(obj).build();
	} 
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postHash(String body) throws ParseException {
		JSONParser parser = new JSONParser();
	    JSONObject obj = (JSONObject)parser.parse(body);
	    byte[] newarray = Base64.getDecoder().decode(((String)obj.get("bytes")).getBytes());
	    if(Arrays.equals(array, newarray))
	    {
	    	return Response.ok().build();
	    } 
	    return Response.status(406).build();
		
	}
	
}
