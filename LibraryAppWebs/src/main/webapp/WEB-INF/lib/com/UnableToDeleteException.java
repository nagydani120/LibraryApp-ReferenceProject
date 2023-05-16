package com;


/**
 * The exception class is created to give an UnableToDeleteException what occurs
 * when someone wants to delete something, what has reference yet to another
 * entity/thing
 * 
 * for example this exception is threw when someone tries to delete a person who
 * has books borrowed in present
 *
 */

@SuppressWarnings("serial")
public class UnableToDeleteException extends Exception {

	public UnableToDeleteException() {
	System.err.println("Unable to delete the entity!");
	}

	
}
