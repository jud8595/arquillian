package com.amundi.tuto.simple;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class HelloSimpleJavaTest {	

    @Deployment(testable = false) // testable=false, ie test case is running inside maven JVM. Purpose is to test webapp from outside the JBoss to see how client interacts with webapp. aka Client mode.
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "emp3.jar")
        		.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testSimple() throws InterruptedException {
    	Assert.fail("Simple Java. Not yet implemented");
    }
}