package br.com.spotippos.errors;

public class DuplicatePropertyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicatePropertyException(String message){
		super(message);
	}
}
