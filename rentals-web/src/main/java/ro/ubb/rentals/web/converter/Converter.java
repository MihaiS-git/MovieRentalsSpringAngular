package ro.ubb.rentals.web.converter;

import ro.ubb.rentals.core.model.BaseEntity;
import ro.ubb.rentals.web.dto.BaseDto;


public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

