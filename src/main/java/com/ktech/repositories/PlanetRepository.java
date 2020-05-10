package com.ktech.repositories;

import com.ktech.entities.Planet;

public class PlanetRepository extends GenericRepository<Planet> {

	public PlanetRepository() {
		super();
	}

	/**
	 * Finds one planet by name
	 * 
	 * @param name
	 * @return
	 */
	public Planet findOneByName(String name) {
		return this.findOneByField("name", name);
	}
}
