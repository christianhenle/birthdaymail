package birthdaymail.domain;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MitarbeiterDetail {
	
	private long id;
	
	private DateTime geburtsdatum;	

}
