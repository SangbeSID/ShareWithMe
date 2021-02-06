package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gestion.Annonce;
import gestion.Profile;
import gestion.User;

class AnnonceTest {
	private Profile admin;
	private Profile sidibe;
	private Annonce a1;
	private Annonce a2;
	
	
	public AnnonceTest() {
		
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	@Before
	public void setUp() {	
		admin = new User("admin", "admin", 1);
		sidibe = new User("sidibe", "sidibe", 0);
		a1 = null;
		a2 = new Annonce("TI92", "Calc pour test", "07-01-2020", sidibe.getNom());		
	}

	
	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testCreationAnnonce1() {
		sidibe.submitAnnouncement("TI92", "", "");
	}
	
	
}
