package birthdaymail.web.ui.mvc;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import birthdaymail.domain.Mitarbeiter;
import birthdaymail.domain.MitarbeiterDetail;
import birthdaymail.repository.MitarbeiterDetailRepository;
import birthdaymail.repository.MitarbeiterRepository;

@RestController
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private MitarbeiterRepository mitarbeiterRepository;
	
	@Autowired
	private MitarbeiterDetailRepository mitarbeiterDetailRepository;

	
	@RequestMapping("/")
	public Mitarbeiter checkForBirthday(){
		
		Mitarbeiter mitarbeiter = new Mitarbeiter();
	
		mitarbeiter.setNachname("nachname");
		mitarbeiter.setPersonalNumer("123456");
		mitarbeiter.setVorname("vorname");
	
		
		MitarbeiterDetail mitarbeiterDetail = new MitarbeiterDetail();

		mitarbeiterDetail.setGeburtsdatum(DateTime.now());
		
		mitarbeiterDetail = mitarbeiterDetailRepository.save(mitarbeiterDetail);
		logger.info(Long.toString(mitarbeiterDetail.getId()));
	
		return mitarbeiterRepository.save(mitarbeiter);
	}

}
