package birthdaymail.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="PERSINFO_MASTER")
public class Mitarbeiter {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="I_PERSID")
	private long id;
	
	@Column(name="S_PERSNR")
	private String personalNumer;
	
	@Column(name="S_FIRSTNAME")
	private String vorname;
	
	@Column(name="S_SURNAME")
	private String nachname;
	
	@OneToOne
	@JoinColumn(name = "I_PERSID" )
	private MitarbeiterDetail mitarbeiterDetail;

}
