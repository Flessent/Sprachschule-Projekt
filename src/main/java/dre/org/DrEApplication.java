package dre.org;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.modelmapper.AbstractConverter;
import org.modelmapper.AbstractProvider;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration

@SpringBootApplication
public class DrEApplication {

	public static Logger logger= LoggerFactory.getLogger(DrEApplication.class);
	@PostConstruct
	public void init() {
		logger.info("Application started...");
	}
	public static void main(String[] args) {
		logger.info("Application executed...");
		SpringApplication.run(DrEApplication.class, args);
	}
	
	
	 @Bean
     public ModelMapper modelMapper() {
         final ModelMapper mapper = new ModelMapper();
         Provider<UUID> uuidProvider = new AbstractProvider<UUID>() {
             @Override
             public UUID get() {
                 return UUID.randomUUID();
             }
         }; 

         final Converter<String, UUID> uuidConverter = new AbstractConverter<>() {
             @Override
             protected UUID convert(final String source) {
                 return UUID.fromString(source);
             }
         };
         mapper.createTypeMap(String.class, UUID.class);
         mapper.addConverter(uuidConverter);
         mapper.getTypeMap(String.class, UUID.class).setProvider(uuidProvider);


        // mapper.getConfiguration()
               //  .setSourceNameTokenizer(NameTokenizers.UNDERSCORE);
                 //.addValueReader(new RecordValueReader());
                 //.setDestinationNameTransformer(NameTransformers.builder("with"))
                // .setDestinationNamingConvention(NamingConventions.builder("with"));
         //mapper.validate();
         return mapper;
     }
	
	
	

}
