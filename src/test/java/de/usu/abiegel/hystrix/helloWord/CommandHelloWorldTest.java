package de.usu.abiegel.hystrix.helloWord;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.abiegel.hystrix.helloWord.CommandHelloWorld;
import rx.Observable;

public class CommandHelloWorldTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCommandHelloWorld() throws InterruptedException, ExecutionException {
		String localString = new CommandHelloWorld("Bob").execute();
		assertEquals("Hello Bob!", localString);
		Future<String> futures = new CommandHelloWorld("Bob").queue();
		assertEquals("Hello Bob!", futures.get());
		Observable<String> observedS = new CommandHelloWorld("Bob").observe();
//		assertEquals("Hello  Bob!", observedS.);
	}

}
