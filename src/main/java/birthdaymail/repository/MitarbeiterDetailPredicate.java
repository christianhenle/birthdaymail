package birthdaymail.repository;

import org.joda.time.DateTime;

import com.querydsl.core.types.dsl.BooleanExpression;

import birthdaymail.domain.QMitarbeiterDetail;

public class MitarbeiterDetailPredicate {

	private static QMitarbeiterDetail mitarbeiterDetail = QMitarbeiterDetail.mitarbeiterDetail;

	public static BooleanExpression mitarbeiterDetailHasDay() {
		DateTime today = new DateTime();

		return mitarbeiterDetail.geburtsdatum.dayOfMonth().eq(today.getDayOfMonth());
	}

	public static BooleanExpression mitarbeiterDetailHasMonth() {
		DateTime today = new DateTime();

		return mitarbeiterDetail.geburtsdatum.month().eq(today.getMonthOfYear());
	}
	
	public static BooleanExpression mitarbeiterTodayBirthday(){
		return mitarbeiterDetailHasDay().and(mitarbeiterDetailHasMonth());
	}

}
