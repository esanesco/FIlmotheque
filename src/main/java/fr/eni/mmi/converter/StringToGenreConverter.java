package fr.eni.mmi.converter;

import fr.eni.bo.Genre;
import fr.eni.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;


import java.lang.annotation.Annotation;

public class StringToGenreConverter implements Converter<String, Genre> {
    private MovieService service;

    @Autowired
    public StringToGenreConverter(MovieService service) {
        this.service = service;
    }

    @Override
    public Genre convert(String id) {
        Long theId = Long.parseLong(id);
        return service.getGenreById(theId);
    }
}
