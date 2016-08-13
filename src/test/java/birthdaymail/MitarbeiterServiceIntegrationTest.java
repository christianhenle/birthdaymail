package birthdaymail;

import static birthdaymail.repository.MitarbeiterDetailPredicate.mitarbeiterDetailHasDay;
import static birthdaymail.repository.MitarbeiterDetailPredicate.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import birthdaymail.domain.Mitarbeiter;
import birthdaymail.domain.MitarbeiterDetail;
import birthdaymail.repository.MitarbeiterDetailRepository;
import birthdaymail.repository.MitarbeiterRepository;
import birthdaymail.service.MitarbeiterService;

public class MitarbeiterServiceIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	private MitarbeiterService mitarbeiterService;

	@Autowired
	private MitarbeiterRepository mitarbeiterRepository;

	@Autowired
	private MitarbeiterDetailRepository mitarbeiterDetailRepository;

	@Test
	public void findMitarbeiter() {
		Long before = mitarbeiterRepository.count();

		Mitarbeiter mitarbeiter = mitarbeiterService.saveMitarbeiter(createMitarbeiter());

		Iterable<Mitarbeiter> mitarbeiterIterable = mitarbeiterRepository.findAll();

		assertThat(mitarbeiterIterable, is(Matchers.<Mitarbeiter>iterableWithSize(before.intValue() + 1)));
		assertThat(mitarbeiterIterable, hasItem(mitarbeiter));

	}

	@Test
	public void findMitarbeiterDetail() {
		Long before = mitarbeiterDetailRepository.count();

		MitarbeiterDetail mitarbeiterDetail = mitarbeiterService.saveMitarbeiter(createMitarbeiter())
				.getMitarbeiterDetail();

		Iterable<MitarbeiterDetail> mitarbeiterDetailIterable = mitarbeiterDetailRepository.findAll();

		assertThat(mitarbeiterDetailIterable, is(Matchers.<MitarbeiterDetail>iterableWithSize(before.intValue() + 1)));
		assertThat(mitarbeiterDetailIterable, hasItem(mitarbeiterDetail));

	}

	@Test
	public void findMitarbeiterDetailDay() {

		Long before = mitarbeiterDetailRepository.count();

		for (Mitarbeiter mitarbeiter : mitarbeiterWithAllDaysOfBirthday()) {
			mitarbeiterService.saveMitarbeiter(mitarbeiter);
		}

		Iterable<MitarbeiterDetail> mitarbeiterDetailIterable = mitarbeiterDetailRepository
				.findAll(mitarbeiterDetailHasDay());

		assertThat(mitarbeiterDetailIterable, is(Matchers.<MitarbeiterDetail>iterableWithSize(before.intValue() + 1)));
		//mitarbeiterDetailIterable.forEach(mitarbeiterDetail -> System.out.println(mitarbeiterDetail.getGeburtsdatum()));
	}

	@Test
	public void findMitarbeiterDetailMonath() {

		Long before = mitarbeiterDetailRepository.count();

		for (Mitarbeiter mitarbeiter : mitarbeiterWithAllMonthOfBirthday()) {
			mitarbeiterService.saveMitarbeiter(mitarbeiter);
		}

		Iterable<MitarbeiterDetail> mitarbeiterDetailIterable = mitarbeiterDetailRepository
				.findAll(mitarbeiterDetailHasMonth());

		assertThat(mitarbeiterDetailIterable, is(Matchers.<MitarbeiterDetail>iterableWithSize(before.intValue() + 1)));
		//mitarbeiterDetailIterable.forEach(mitarbeiterDetail -> System.out.println(mitarbeiterDetail.getGeburtsdatum()));
	}

	@Test
	public void findMitarbeiterWithTodayBirthday() {
		Long before = mitarbeiterDetailRepository.count();

		Mitarbeiter mitarbeiter = mitarbeiterService.saveMitarbeiter(createMitarbeiterTodayBirthday());

		Iterable<MitarbeiterDetail> mitarbeiterDetailIterable = mitarbeiterDetailRepository
				.findAll(mitarbeiterTodayBirthday());

		assertThat(mitarbeiterDetailIterable, is(Matchers.<MitarbeiterDetail>iterableWithSize(before.intValue() + 1)));
		//mitarbeiterDetailIterable.forEach(mitarbeiterDetail -> System.out.println(mitarbeiterDetail.getGeburtsdatum()));
		assertThat(mitarbeiterDetailIterable, hasItem(mitarbeiter.getMitarbeiterDetail()));
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

	public static Mitarbeiter createMitarbeiterTodayBirthday() {
		Mitarbeiter mitarbeiter = new Mitarbeiter();

		mitarbeiter.setNachname("nachname");
		mitarbeiter.setPersonalNumer("123456");
		mitarbeiter.setVorname("vorname");

		MitarbeiterDetail mitarbeiterDetail = new MitarbeiterDetail();

		DateTime today = new DateTime();
		today = today.minusYears(10);
		mitarbeiterDetail.setGeburtsdatum(today);

		mitarbeiter.setMitarbeiterDetail(mitarbeiterDetail);
		return mitarbeiter;
	}

	public static List<Mitarbeiter> mitarbeiterWithAllDaysOfBirthday() {
		List<Mitarbeiter> mitarbeiterList = new ArrayList<>();

		Mitarbeiter mitarbeiter;
		MitarbeiterDetail mitarbeiterDetail;

		for (int i = 1; i <= 31; i++) {
			System.out.println("i " + i);
			LocalDate todayLocalDate = new LocalDate().withDayOfMonth(i);
			DateTime today = todayLocalDate.toDateTimeAtStartOfDay();

			mitarbeiter = new Mitarbeiter();

			mitarbeiter.setNachname("nachname");
			mitarbeiter.setPersonalNumer("123456");
			mitarbeiter.setVorname("vorname");

			mitarbeiterDetail = new MitarbeiterDetail();

			mitarbeiterDetail.setGeburtsdatum(today);

			mitarbeiter.setMitarbeiterDetail(mitarbeiterDetail);
			mitarbeiterList.add(mitarbeiter);

		}
		return mitarbeiterList;

	}

	public static List<Mitarbeiter> mitarbeiterWithAllMonthOfBirthday() {
		List<Mitarbeiter> mitarbeiterList = new ArrayList<>();

		Mitarbeiter mitarbeiter;
		MitarbeiterDetail mitarbeiterDetail;

		for (int i = 1; i <= 12; i++) {
			System.out.println("i " + i);
			LocalDate todayLocalDate = new LocalDate().withMonthOfYear(i);
			DateTime today = todayLocalDate.toDateTimeAtStartOfDay();

			mitarbeiter = new Mitarbeiter();

			mitarbeiter.setNachname("nachname");
			mitarbeiter.setPersonalNumer("123456");
			mitarbeiter.setVorname("vorname");

			mitarbeiterDetail = new MitarbeiterDetail();

			mitarbeiterDetail.setGeburtsdatum(today);

			mitarbeiter.setMitarbeiterDetail(mitarbeiterDetail);
			mitarbeiterList.add(mitarbeiter);

		}
		return mitarbeiterList;

	}

}
