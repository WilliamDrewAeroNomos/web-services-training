/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

/**
 * 
 * @author <a href=mailto:support@aeronomos.org>aeronomos</a>
 *
 *         $Id: $
 * 
 */
public class HelloWorldBean {

	private String message;

	public HelloWorldBean(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("HellowWorldBean [message=%s]" + message);
	}

}
