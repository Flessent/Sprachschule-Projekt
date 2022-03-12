package dre.org.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dre.org.dto.KursDTO;
import dre.org.entities.Kurs;
import dre.org.entities.Niveau;
import dre.org.entities.Raum;
import dre.org.entities.Sprache;
import dre.org.services.KursServices;
import dre.org.services.NiveauServices;
import dre.org.services.RaumServices;
import dre.org.services.SpracheServices;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/kurs")
public class KursRestService extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	public NiveauServices niveauServices;
@Autowired
public KursServices kursServices;
@Autowired
public RaumServices raumServices;
@Autowired
public SpracheServices spracheServices;

@Autowired
private ModelMapper modelMapper;

Converter<Kurs, UUID> uuidConverterKurs = new AbstractConverter<Kurs, UUID>() {
    protected UUID convert(Kurs source) {
        return UUID.fromString(source.getCodeKurs().toString());
    }
};

Converter<Sprache, UUID> uuidConverterSprache = new AbstractConverter<Sprache, UUID>() {
    protected UUID convert(Sprache source) {
        return UUID.fromString(source.getCodeSprache().toString());
    }
};


Converter<Raum, UUID> uuidConverterRaum = new AbstractConverter<Raum, UUID>() {
    protected UUID convert(Raum source) {
        return UUID.fromString(source.getCodeRaum().toString());
    }
};

Converter<Niveau, UUID> uuidConverterNiveau = new AbstractConverter<Niveau, UUID>() {
    protected UUID convert(Niveau source) {
        return UUID.fromString(source.getCodeNiveau().toString());
    }
};


private KursDTO convertEntityToDto(Kurs kurs){
	modelMapper.addConverter(uuidConverterKurs);
	modelMapper.addConverter(uuidConverterSprache);
	modelMapper.addConverter(uuidConverterRaum);
	modelMapper.addConverter(uuidConverterNiveau);
    modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.LOOSE);
    KursDTO kursDTO = new KursDTO();
    kursDTO = modelMapper.map(kurs, KursDTO.class);
   //kurs.setNiveau(niveauServices.getAllNiveauByUUID(kursDTO.getNiveau()));
   
    /*kurs.getNiveau().forEach(niveau -> {
    	listeUUID.add(niveau.getCodeNiveau());
    });
    System.out.print("la liste UUID Niv dans convert"+listeUUID);
    kursDTO.setNiveau(listeUUID);*/

    return kursDTO;
}

private Kurs convertDtoToEntity(KursDTO kursDTO){

    modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.LOOSE);
    Kurs kurs = new Kurs();
    kurs = modelMapper.map(kursDTO, Kurs.class);
    Set<Niveau> listeNiveau=niveauServices.getAllNiveauByUUID(kursDTO.getNiveau());
    
    kurs.setNiveau(listeNiveau);    

    Set<Raum> listeRaum=raumServices.getAllRaumByUUID(kursDTO.getRaum());
    

    kurs.setRaum(listeRaum);
    Sprache sprache = spracheServices.getOneSpracheByCodeSprache(kursDTO.getLangue());
    kurs.setLangue(sprache);
    return kurs;
}


	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@PostMapping(path = "/saveKurs", produces = "application/json")
	public @ResponseBody Object saveKurs(@RequestBody @Valid KursDTO kursDTO,BindingResult bindingResult) {
		//niveauServices.getCodeNiveauByLibelle(kursDTO.setNiveau(null));
		System.out.print("le kurs est"+kursDTO);
		
		return kursServices.saveKurs(convertDtoToEntity(kursDTO), bindingResult);
	}

	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@PatchMapping(path = "/updateKurs")
	public ResponseEntity<?> updateKurs(@RequestBody KursDTO kurs) {
	
	    return new  ResponseEntity<>(kursServices.updateKurs(convertDtoToEntity(kurs)),HttpStatus.OK);
	}
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@DeleteMapping(path  = "/deleteKurs/{codeKurs}")
	public ResponseEntity<?> deleteKurs(@PathVariable("codeKurs") UUID codeKurs){
		kursServices.deleteKurs(codeKurs);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	



	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@GetMapping(path = "/listeKurs")
	public ResponseEntity<List<KursDTO>> listeKurs(){
		List<KursDTO> listKursDTO=new ArrayList<KursDTO>();
		listKursDTO= kursServices.getAllKurs().stream().map(this::convertEntityToDto).collect(Collectors.toList());;
		return new ResponseEntity<>(listKursDTO,HttpStatus.OK);

	}
	
	
	@Secured(value= {"ROLE_VERWALTER","ROLE_STUDENT","ROLE_LEHRER","ROLE_LEITER"})
	@GetMapping(path = "/getKursByCodeKurs/{codeKurs}")
	public ResponseEntity<KursDTO> getOneKurs(@PathVariable("codeKurs")  UUID codeKurs){
		
		return new ResponseEntity<>(convertEntityToDto(kursServices.getOneKursByCodeKurs(codeKurs)),HttpStatus.OK);
	}
	
	
}
