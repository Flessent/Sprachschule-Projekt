package dre.org.dto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

import dre.org.entities.*;
@Component
public class Raum_ConverterUUID implements Converter<String, Raum> {
	@Override
	public Raum convert(@NonNull String uuid) {
		return new Raum(UUID.fromString(uuid)); 
	}
}
