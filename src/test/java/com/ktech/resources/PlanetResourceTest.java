package com.ktech.resources;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.ktech.entities.Planet;
import com.ktech.repositories.PlanetRepository;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("unchecked")
public class PlanetResourceTest {
	private static PlanetResource resource;
	private static PlanetRepository repository;
	 
	@BeforeClass
	public static void setUp() {
		repository = mock(PlanetRepository.class);
		resource = new PlanetResource(repository);
	}
	
	@Test
	public void getAll() {
		String name = "Aleal";
		String climate = "Hot as hell";
		String terrain = "Desert";
		int statusCode = 200;
		when(repository.findAll()).thenReturn(Arrays.asList(new Planet(name, climate, terrain)));
		Response response = resource.getAll();
		List<Planet> planetList = (List<Planet>) response.getEntity();
		assertEquals(statusCode, response.getStatus());
		assertEquals(name, planetList.get(0).getName());
	}
	
	@Test
	public void getById() {
		String id = "1";
		String name = "Aleal";
		String climate = "Hot as hell";
		String terrain = "Desert";
		int statusCode = Status.OK.getStatusCode();
		when(repository.findById(id)).thenReturn(new Planet(id, name, climate, terrain, 0));
		Response response = resource.getById(id);
		Planet planet = (Planet) response.getEntity();
		assertEquals(statusCode, response.getStatus());
		assertEquals(id, planet.getId());
	}
	
	@Test
	public void getByIdReturnsNotFound() {
		String id = "1";
		int statusCode = Status.NOT_FOUND.getStatusCode();
		when(repository.findById(id)).thenReturn(null);
		Response response = resource.getById(id);
		assertEquals(statusCode, response.getStatus());
	}
	
	@Test
	public void getByName() {
		String id = "1";
		String name = "Aleal";
		String climate = "Hot as hell";
		String terrain = "Desert";
		int statusCode = Status.OK.getStatusCode();
		when(repository.findOneByName(name)).thenReturn(new Planet(id, name, climate, terrain, 0));
		Response response = resource.getByName(name);
		Planet planet = (Planet) response.getEntity();
		assertEquals(statusCode, response.getStatus());
		assertEquals(id, planet.getId());
	}
	
	@Test
	public void getByNameReturnsNotFound() {
		String name = "Aleal";
		int statusCode = Status.NOT_FOUND.getStatusCode();
		when(repository.findOneByName(name)).thenReturn(null);
		Response response = resource.getByName(name);
		assertEquals(statusCode, response.getStatus());
	}
	

	@Test
	public void create() {
		String name = "Aleal";
		String climate = "Hot as hell";
		String terrain = "Desert";
		Planet planet = new Planet(name, climate, terrain);
		int statusCode = Status.OK.getStatusCode();
		when(repository.findOneByName(name)).thenReturn(null);
		when(repository.create(planet)).thenReturn(planet);
		Response response = resource.create(planet);
		Planet planetCreated = (Planet) response.getEntity();
		assertEquals(statusCode, response.getStatus());
		assertEquals(name, planetCreated.getName());
		assertEquals(climate, planetCreated.getClimate());
		assertEquals(terrain, planetCreated.getTerrain());
	}

	@Test
	public void delete() {
		String id = "1";
		String name = "Aleal";
		String climate = "Hot as hell";
		String terrain = "Desert";
		Planet planet = new Planet(id, name, climate, terrain, 0);
		int statusCode = Status.OK.getStatusCode();
		when(repository.delete(id)).thenReturn(planet);
		Response response = resource.delete(id);
		Planet planetdeleted = (Planet) response.getEntity();
		assertEquals(statusCode, response.getStatus());
		assertEquals(id, planetdeleted.getId());
	}

	@Test
	public void deleteReturnsNotFound() {
		String id = "1";
		int statusCode = Status.NOT_FOUND.getStatusCode();
		when(repository.delete(id)).thenReturn(null);
		Response response = resource.delete(id);
		assertEquals(statusCode, response.getStatus());

	}
}
