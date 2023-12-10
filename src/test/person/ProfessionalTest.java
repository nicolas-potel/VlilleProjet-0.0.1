package test.person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;

import vlille.person.*;

class ProfessionalTest {
	
	private ProfessionalMock p;

	@BeforeEach
	public void init() {
		p = new ProfessionalMock(0);
	}
	
	
	@Test
	public void testInitWentGood() {
		assertEquals(0, p.getId());
	}
	

}
