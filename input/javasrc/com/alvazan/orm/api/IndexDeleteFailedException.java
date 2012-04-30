package com.alvazan.orm.api;

import java.util.List;

public class IndexDeleteFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private List<IndexErrorInfo> errors;

	public IndexDeleteFailedException() {
		super();
	}

	public IndexDeleteFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public IndexDeleteFailedException(String message) {
		super(message);
	}

	public IndexDeleteFailedException(Throwable cause) {
		super(cause);
	}

	public IndexDeleteFailedException(List<IndexErrorInfo> exceptions) {
		this.errors = exceptions;
	}

	@Override
	public String getMessage() {
		String msg = "Removes from the index failed.  Items will not be deleted/removed from\n" +
				" database, but previous index removes may have succeeded without actually removing" +
				" entites and those may need to be rebuilt.\n" +
				"  Call getExceptions to get the ids that failed AND the Exception that caused the failure\n" +
				"In the meantime, here is a list of the ids that failed removal and the message of failure...\n\n";
		
		for(IndexErrorInfo info : errors) {
			msg += "items list(total="+info.getItemsThatFailed().size()+")=\n";
			for(String id : info.getIdsThatFailed()) {
				msg += "id="+id+"\n";
			}
			msg+="failure="+info.getCause().getMessage()+"\n\n\n";
		}
		return msg;
	}

	public List<IndexErrorInfo> getExceptions() {
		return errors;
	}
	
}