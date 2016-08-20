package birthdaymail.web.ui.mvc;

import static birthdaymail.repository.MitarbeiterDetailPredicate.mitarbeiterTodayBirthday;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import birthdaymail.domain.Mitarbeiter;
import birthdaymail.mail.SmtpMailSender;
import birthdaymail.repository.MitarbeiterRepository;
import birthdaymail.service.MitarbeiterService;

@RestController
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private MitarbeiterService mitarbeiterService;

	@Autowired
	private MitarbeiterRepository mitarbeiterRepository;

	@Autowired
	private SmtpMailSender smtpMailSender;

	@RequestMapping("/")
	public Iterable<Mitarbeiter> checkForBirthday() {

		return mitarbeiterRepository.findAll(mitarbeiterTodayBirthday());
	}

	@Scheduled
	@RequestMapping("/sendMail")
	public void executeBirthdayCheck() {
		Iterable<Mitarbeiter> mitarbeiterList = checkForBirthday();

		//Don't send Mail nobody has birthday
		if (mitarbeiterList.iterator().hasNext()) {

			String body = createMailBody(mitarbeiterList);
			smtpMailSender.send("christian.henle@gmx.de", "Geburtstagsbenachrichtigung", body);

		}
	}

	private String createMailBody(Iterable<Mitarbeiter> mitarbeiterList) {
		StringBuilder sb = new StringBuilder();
		mitarbeiterList.forEach(m -> sb.append(m + "\n "));

		String body = "Hallo,\n heute haben folgende Mitarbeiter Geburtstag" + sb.toString();

		return body;
	}

}
