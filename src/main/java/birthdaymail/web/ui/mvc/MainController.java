package birthdaymail.web.ui.mvc;

import static birthdaymail.repository.MitarbeiterDetailPredicate.mitarbeiterTodayBirthday;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import birthdaymail.domain.Mitarbeiter;
import birthdaymail.repository.MitarbeiterRepository;
import birthdaymail.service.MitarbeiterService;

@RestController
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private MitarbeiterService mitarbeiterService;
	
	@Autowired
	private MitarbeiterRepository mitarbeiterRepository;
	
	
	@RequestMapping("/")
	public Iterable<Mitarbeiter> checkForBirthday(){
		
		return mitarbeiterRepository.findAll(mitarbeiterTodayBirthday());
		//TODO Iterable Mitarbeiter
	}

}
