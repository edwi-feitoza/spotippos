package br.com.spotippos.errors;

import java.util.ArrayList;
import java.util.List;

class ErrorWrapper {
	private final String rootCause;
	private List<ErrorMessage> errors = new ArrayList<>();

	ErrorWrapper(String rootCause) {
		this.rootCause = rootCause;
	}

	public String getRootCause() {
		return rootCause;
	}

	public List<ErrorMessage> getErrors() {
		return errors;
	}

	public void addErrors(String errorMessage) {
		this.errors.add(new ErrorMessage(errorMessage));
	}

	static class ErrorMessage {
		private String message;

		public ErrorMessage(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}
}