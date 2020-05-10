package com.ktech.entities;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Field;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Index;
import dev.morphia.annotations.Indexes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * Entity that represents a Planet object
 * 
 * @author aleal
 *
 */
@Entity("Planets")
@Indexes(@Index(fields = @Field("name")))
public class Planet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Climate is required")
	private String climate;
	@NotBlank(message = "Terrain is required")
	private String terrain;
	private Integer filmAppearances;

	public Planet(String name, String climate, String terrain) {
		super();
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}

}