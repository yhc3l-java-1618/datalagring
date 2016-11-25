package se.coredev.jdbc.recap.repository;

public final class RepositoryException extends Exception {

	private static final long serialVersionUID = -6376841914075004640L;

	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoryException(Throwable cause) {
		super(cause);
	}

	public RepositoryException(String message) {
		super(message);
	}
	
}
