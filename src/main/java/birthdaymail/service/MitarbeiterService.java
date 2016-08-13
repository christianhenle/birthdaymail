package birthdaymail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import birthdaymail.domain.Mitarbeiter;
import birthdaymail.repository.MitarbeiterDetailRepository;
import birthdaymail.repository.MitarbeiterRepository;

@Service
public class MitarbeiterService {
	
	@Autowired
	private MitarbeiterRepository mitarbeiterRepository;
	
	@Autowired
	private MitarbeiterDetailRepository mitarbeiterDetailRepository;

	@Transactional
	public Mitarbeiter saveMitarbeiter(Mitarbeiter mitarbeiter)
	{
		mitarbeiter = mitarbeiterRepository.save(mitarbeiter);
		mitarbeiterDetailRepository.save(mitarbeiter.getMitarbeiterDetail());
		
		return mitarbeiter;
	}

}
