package dre.org.dto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

import dre.org.entities.*;
@Component
public class Kurs_ConverterUUID implements Converter<String, Kurs> {
	@Override
	public Kurs convert(@NonNull String uuid) {
		return new Kurs(UUID.fromString(uuid)); 
	}
}
