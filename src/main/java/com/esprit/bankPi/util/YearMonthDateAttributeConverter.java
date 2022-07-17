package com.esprit.bankPi.util;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;

import javax.persistence.AttributeConverter;

public class YearMonthDateAttributeConverter implements AttributeConverter<YearMonth, java.sql.Date> {

	@Override
	public java.sql.Date convertToDatabaseColumn(YearMonth attribute) {
		return java.sql.Date.valueOf(attribute.atDay(1));
	}

	@Override
	public YearMonth convertToEntityAttribute(java.sql.Date dbData) {
		return YearMonth.from(Instant.ofEpochMilli(dbData.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
	}
}