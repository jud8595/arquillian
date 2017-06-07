package com.amundi.tuto.simple;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class HelloSimpleWebAndInjectTest {	

    @Deployment(testable = false) // testable=false, ie test case is running inside maven JVM. Purpose is to test webapp from outside the JBoss to see how client interacts with webapp. aka Client mode.
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "emp3.war")
        		.addClass(SimpleServiceImpl.class)
        		.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml"); // for inject only
    }
    
    @Inject
    private SimpleServiceImpl service;

    @Test
    public void testSimple() throws InterruptedException {
    	// Inject ne marche pas en testable=false
    	Assert.assertNotNull(service);
    	Assert.fail("Simple Web. Not yet implemented");
    }
}