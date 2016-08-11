package birthdaymail.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mitarbeiter {

	private long id;

	private String personalNumer;

	private String vorname;

	private String nachname;

	private MitarbeiterDetail mitarbeiterDetail;

}
