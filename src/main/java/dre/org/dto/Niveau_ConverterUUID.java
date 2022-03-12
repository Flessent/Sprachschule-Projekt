package dre.org.dto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

import dre.org.entities.*;
@Component
public class Niveau_ConverterUUID implements Converter<String, Niveau> {
	@Override
	public Niveau convert(@NonNull String uuid) {
		return new Niveau(UUID.fromString(uuid)); 
	}
}
