package com.amundi.tuto.simple;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolverSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class HelloSimpleResourceMockTest {	

    @Deployment(testable = false) // testable=false, ie test case is running inside maven JVM. Purpose is to test webapp from outside the JBoss to see how client interacts with webapp. aka Client mode.
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "emp3.war")
        		.addClass(HelloResourceInject.class)
        		.addClass(MockProducer.class)
        		.addClass(SimpleService.class)
        		.addClass(RegistryApplication.class)
        		.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml"); // for inject only
        MavenResolverSystem resolver = Maven.resolver();
        war.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("org.mockito:mockito-all:1.10.19").withTransitivity().asSingleFile());
        return war;
    }
    
    private WebTarget target;
    
    @ArquillianResource
    private URL base;

    @Before
    public void setUp() throws MalformedURLException {
        Client client = ClientBuilder.newClient();
        target = client.target(URI.create(new URL(base, "hello").toExternalForm()));
    }

    @Test
    public void testSimple() throws InterruptedException {
    	String str = target.request().get(String.class);
    	Assert.assertEquals("Hello, Resource. Service: ---------mock Simple", str);
    	Assert.fail("Simple Resource Mock. Not yet implemented");
    }
}