package birthdaymail.web.ui.mvc;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import birthdaymail.domain.Mitarbeiter;
import birthdaymail.domain.MitarbeiterDetail;
import birthdaymail.service.MitarbeiterService;

@RestController
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private MitarbeiterService mitarbeiterService;
	
	
	@RequestMapping("/")
	public void checkForBirthday(){
		
		//TODO Iterable Mitarbeiter
	}

}
