package birthdaymail.web.ui.mvc;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import birthdaymail.domain.Mitarbeiter;
import birthdaymail.domain.MitarbeiterDetail;

@RestController
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping("/")
	public Mitarbeiter checkForBirthday(){
		
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setId(1);
		mitarbeiter.setNachname("nachname");
		mitarbeiter.setPersonalNumer("123456");
		mitarbeiter.setVorname("vorname");
	
		
		MitarbeiterDetail mitarbeiterDetail = new MitarbeiterDetail();
		mitarbeiterDetail.setId(1);
		mitarbeiterDetail.setGeburtsdatum(DateTime.now());
		
		mitarbeiter.setMitarbeiterDetail(mitarbeiterDetail);
	
		return mitarbeiter;
	}

}
