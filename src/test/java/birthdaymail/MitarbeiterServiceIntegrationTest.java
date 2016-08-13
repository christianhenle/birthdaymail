package birthdaymail;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import birthdaymail.domain.Mitarbeiter;
import birthdaymail.domain.MitarbeiterDetail;
import birthdaymail.repository.MitarbeiterRepository;
import birthdaymail.service.MitarbeiterService;


public class MitarbeiterServiceIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	private MitarbeiterService mitarbeiterService;
	
	@Autowired
	private MitarbeiterRepository mitarbeiterRepository;

	@Test
	public void createNewMitarbeiter() 
	{
		Long before = mitarbeiterRepository.count();		

		Mitarbeiter mitarbeiter = mitarbeiterService.saveMitarbeiter(createMitarbeiter());
		
		Iterable<Mitarbeiter> mitarbeiterIterable = mitarbeiterRepository.findAll();
		assertThat(mitarbeiterIterable, is(Matchers.<Mitarbeiter> iterableWithSize(before.intValue() + 1)));
		assertThat(mitarbeiterIterable, hasItem(mitarbeiter));
	}

	public static Mitarbeiter createMitarbeiter() {
		Mitarbeiter mitarbeiter = new Mitarbeiter();

		mitarbeiter.setNachname("nachname");
		mitarbeiter.setPersonalNumer("123456");
		mitarbeiter.setVorname("vorname");

		MitarbeiterDetail mitarbeiterDetail = new MitarbeiterDetail();
		mitarbeiterDetail.setGeburtsdatum(DateTime.now());

		mitarbeiter.setMitarbeiterDetail(mitarbeiterDetail);
		return mitarbeiter;
	}

}
