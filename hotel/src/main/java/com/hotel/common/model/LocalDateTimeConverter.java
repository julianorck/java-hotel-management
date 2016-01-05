package com.hotel.common.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Faces converter for support of LocalDate
 * 
 */
@FacesConverter(value = "localDateTimeConverter")
public class LocalDateTimeConverter implements javax.faces.convert.Converter {

	@Override
	public Object getAsObject(final FacesContext context, final UIComponent component, final String stringValue) {

		if (null == stringValue || stringValue.isEmpty()) {
			return null;
		}

		LocalDateTime localDateTime = null;

		try {

			localDateTime = LocalDateTime.parse(
					stringValue.trim(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

		} catch (final DateTimeParseException e) {

			throw new ConverterException("O formato final da data e final hora deve ser 13-11-2015 12:00");
		}

		return localDateTime;

	}

	@Override
	public String getAsString(final FacesContext context, final UIComponent component,
			final Object localDateTimeValue) {

		if (null == localDateTimeValue) {

			return "";
		}

		return ((LocalDateTime) localDateTimeValue)
				.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	}

}
