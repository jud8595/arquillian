package com.amundi.tuto.simple;

import javax.enterprise.inject.Produces;

import org.mockito.Mockito;

public class MockProducer {

	@Produces
	public SimpleService simpleServiceProducer() {
		SimpleService mock = Mockito.mock(SimpleService.class);
        Mockito.when(mock.simple()).thenReturn("---------mock Simple");
        return mock;
		//return new SimpleServiceImpl();
	}
}
