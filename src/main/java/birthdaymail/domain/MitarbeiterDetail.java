package birthdaymail.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MitarbeiterDetail {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime geburtsdatum;

	//private Mitarbeiter mitarbeiter;

}
