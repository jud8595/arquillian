package com.amundi.tuto.simple;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolverSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(Arquillian.class)
public class HelloSimpleWebAndMockTest {	

    @Deployment//(testable = false) // testable=false, ie test case is running inside maven JVM. Purpose is to test webapp from outside the JBoss to see how client interacts with webapp. aka Client mode.
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "emp3.war")
        		.addClass(SimpleService.class)
        		.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml"); // for inject only
        MavenResolverSystem resolver = Maven.resolver();
        war.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("org.mockito:mockito-all:1.10.19").withTransitivity().asSingleFile());
        return war;
    }
    
    private SimpleService mock;
    
    @Before
    public void setupMock() {
    	mock = Mockito.mock(SimpleService.class);
        Mockito.when(mock.simple()).thenReturn("---------mock Simple");
    }

    @Test
    public void testSimple() throws InterruptedException {
    	String str = mock.simple();
    	System.out.println(str);
    	Assert.assertEquals("---------mock Simple", str);
    	// Inject ne marche pas en testable=false
    	Assert.assertNotNull(mock);
    	Assert.fail("Simple Web. Not yet implemented");
    }
}