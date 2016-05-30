package edu.ubb.cs.idde.inter;

public class RepositoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	//private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RepositoryException.class);
	
	public RepositoryException() {
		super();
	}

	public RepositoryException(final String message) {
		super(message);
		//LOG.error(message);
	}

	public RepositoryException(final String message, final Throwable cause) {
		super(message, cause);
		//LOG.error(message+ ": " +cause);
	}
}