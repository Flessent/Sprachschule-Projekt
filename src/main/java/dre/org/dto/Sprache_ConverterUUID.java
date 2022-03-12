package dre.org.dto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;

import dre.org.entities.*;
@Component
public class Sprache_ConverterUUID implements Converter<String, Sprache> {
	@Override
	public Sprache convert(@NonNull String uuid) {
		return new Sprache(UUID.fromString(uuid)); 
	}
}
