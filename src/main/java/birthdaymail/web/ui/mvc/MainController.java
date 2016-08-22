package birthdaymail.web.ui.mvc;

import static birthdaymail.repository.MitarbeiterDetailPredicate.mitarbeiterTodayBirthday;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import birthdaymail.domain.Mitarbeiter;
import birthdaymail.mail.SmtpMailSender;
import birthdaymail.repository.MitarbeiterRepository;

@RestController
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private MitarbeiterRepository mitarbeiterRepository;

	@Autowired
	private SmtpMailSender smtpMailSender;

	@Value("${birthdaymail.eMailTo}")
	private String[] eMailTo;

	@Value("${birthdaymail.eMailSubject}")
	private String eMailSubject;

	@RequestMapping("/")
	public Iterable<Mitarbeiter> checkForBirthday() {

		return mitarbeiterRepository.findAll(mitarbeiterTodayBirthday());
	}

	
	@Scheduled(cron = "${birthdaymail.cron.birthday.check}", zone = "Europe/Berlin")
	@RequestMapping("/sendMail")
	public void executeBirthdayCheck() {

		Iterable<Mitarbeiter> mitarbeiterList = checkForBirthday();

		// Don't send Mail nobody has birthday
		if (mitarbeiterList.iterator().hasNext()) {

			String body = createMailBody(mitarbeiterList);
			smtpMailSender.send(eMailTo, eMailSubject, body);
		}

	}

	private String createMailBody(Iterable<Mitarbeiter> mitarbeiterList) {
		StringBuilder sb = new StringBuilder();

		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy");

		mitarbeiterList.forEach(m -> sb.append("<p>" + m.getVorname() + " " + m.getNachname() + " "
				+ fmt.print(m.getMitarbeiterDetail().getGeburtsdatum()) + "</p>"));

		String body = "<html><body><p>Hallo, </p><p>heute haben folgende Mitarbeiter Geburtstag:</p>" + sb.toString()
				+ "</body></html>";

		return body;
	}

}
