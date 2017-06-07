package com.amundi.tuto.simple;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class HelloResourceInject {

	@Inject
	private SimpleService service;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello() {
		System.out.println("Hello Resource");
		return "Hello, Resource. Service: " + service.simple();
	}
	
}
