package com.ktech.repositories;

import com.ktech.entities.Planet;

public class PlanetRepository extends GenericRepository<Planet> {

	public PlanetRepository() {
		super();
	}

	/**
	 * Gets one planet by name
	 * 
	 * @param name
	 * @return
	 */
	public Planet getOneByName(String name) {
		return this.getOneByField("name", name);
	}
}
