package se.coredev.jdbc.recap.service;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -6942041652404383984L;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	
}
