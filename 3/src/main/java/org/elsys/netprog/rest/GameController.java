package org.elsys.netprog.rest;

import java.net.URI;
import org.json.simple.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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


@Path("/game")
public class GameController {
	static List<game> games = new ArrayList<game>();
	@POST
	@Path("/startGame")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response startGame() throws URISyntaxException{
		game newGame = new game(games.size());
		games.add(newGame);
		return Response.created(new URI("/games")).entity(newGame.gameId).build();
	}
	
	@PUT
	@Path("/guess/{id}/{guess}")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response guess(@PathParam("id") String gameId, @PathParam("guess") String guess) throws Exception{
		int cows, bulls, x;
		for(game g : games) {
			if (g.gameId.equals(Integer.parseInt(gameId)))
			{
				if(guess.charAt(0) == '0')
				{
					return Response.status(400).build();
				}
				try {
					if(!if_valid(Integer.parseInt(guess))) {
						return Response.status(400).build();
					}
				} catch(NumberFormatException a) {
					return Response.status(400).build();
				}
				x = g.cowsAndBulls(Integer.parseInt(guess));
				cows = x / 10;
				bulls = x % 10;
				JSONObject json = new JSONObject();
				json.put("gameId", g.gameId.toString());
				json.put("cowsNumber", cows);
				json.put("bullsNumber", bulls);
				json.put("turnsCount", g.count);
				json.put("success", g.success);
				return Response.status(200).entity(json).build();
			}
		}
		return Response.status(404).build();
	}
	
	@GET
	@Path("/games")
	@Produces(value={MediaType.APPLICATION_JSON})
	public Response getGames() {
		String secret = "****";
		JSONObject json = new JSONObject();
		JSONArray jo = new JSONArray();
		for(game g : games) {
			if(g.success == true) {
				json.put("gameId", g.gameId.toString());
				json.put("turnsCount", g.count);
				json.put("secret", g.number.toString());
				json.put("success", g.success);
			} else {
				json.put("gameId", g.gameId.toString());
				json.put("turnsCount", g.count);
				json.put("secret", secret);
				json.put("success", g.success);
			}
			jo.add(json);
		}
		if(jo.size() == 0) {
			return Response.status(404).build();
		}
		
		return Response.status(200).entity(jo).build();
	}
	
	public Boolean if_valid(Integer number)
	{
		Integer a, b, c, d;
		a = number / 1000;
		b = (number / 100) - a*10;
		c = (number / 10) - a*100 - b*10;
		d = number - a*1000 - b*100 - c*10;
		Set <Integer> set = new HashSet<Integer>();
		set.add(a);
		set.add(b);
		set.add(c);
		set.add(d);
		if(set.size() != 4)
		{
			return false;
		}
		return true;
		
	}
	
}

