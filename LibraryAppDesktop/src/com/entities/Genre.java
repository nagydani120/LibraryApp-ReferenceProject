package com.entities;

import java.util.ArrayList;

public enum Genre {

	FANTASY, MYSTERY, THRILLER, ROMANCE, WESTERN,
	DYSTOPIAN, DRAMA, SCIENCE, HORROR, FICTION;


	public static ArrayList<Genre> getGenres() {
		ArrayList<Genre> genres = new ArrayList<>();
		genres.add(MYSTERY);
		genres.add(THRILLER);
		genres.add(ROMANCE);
		genres.add(WESTERN);
		genres.add(DYSTOPIAN);
		genres.add(DRAMA);
		genres.add(SCIENCE);
		genres.add(HORROR);
		genres.add(FICTION);
		return genres;
	}

//return the name of constant (first letter uppercase, remain lowercase) 
	@Override
	public String toString() {
		String name = this.name();
		return name.charAt(0) + name.substring(1, name.length())
				.toLowerCase();

	}

}
