package birthdaymail.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="PERSINFO_DETAIL")
public class MitarbeiterDetail {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="I_PERSID")
	private long id;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name="D_BIRTHDAY")
	private DateTime geburtsdatum;

	@JsonIgnore
	@OneToOne(mappedBy="mitarbeiterDetail", cascade = {CascadeType.ALL})
	private Mitarbeiter mitarbeiter;

}
