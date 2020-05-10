package com.ktech.resources;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ktech.entities.Planet;
import com.ktech.repositories.PlanetRepository;

/**
 * Planet routes
 */
@Path("planets")
public class PlanetResource extends GenericResource<Planet, PlanetRepository> {

	@Inject
	public PlanetResource(PlanetRepository repository) {
		super(repository);
	}

	/**
	 * gets a planet by its name
	 * 
	 * @param name
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("name/{name}")
	public Response getByName(@PathParam("name") String name) {
		Planet planet = this.repository.findOneByName(name);
		if (Objects.isNull(planet)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(planet).build();
	}
	
	
	@GET
	@Path("seed")
	@Produces(MediaType.APPLICATION_JSON)
	public Response applySeed() {
		List<Planet> planets = Arrays.asList(
		 new Planet("Tatooine", "arid", "desert", 5),
		 new Planet("Alderaan", "temperate", "grasslands, mountains", 2),
		 new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests", 1),
		 new Planet("Hoth", "frozen", "tundra, ice caves, mountain ranges", 1),
		 new Planet("Dagobah", "murky", "swamp, jungles", 3),
		 new Planet("Naboo", "temperate", "grassy hills, swamps, forests, mountains", 4)
		);
		System.out.println(planets);
		return  Response.ok(this.repository.createAll(planets)).build();
	
	}
}
