package test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import happyChick.config.AppConfig;
import happyChick.service.PoulaillerService;
import happyChick.service.PouleService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class})
public class TestCase {
	@Autowired
	PoulaillerService poulaillerService;
	
	@Autowired
	PouleService pouleService;
	
	@Test
	public void testPoulaillerService() {
		assertNotNull(poulaillerService);
	}
	
	@Test
	public void testPouleService() {
		assertNotNull(pouleService);
	}
}
