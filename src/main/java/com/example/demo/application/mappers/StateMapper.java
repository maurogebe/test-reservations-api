package com.example.demo.application.mappers;

import com.example.demo.application.dtos.StateDTO;
import com.example.demo.domain.entities.State;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StateMapper {

    StateMapper INSTANCE = Mappers.getMapper( StateMapper.class );

    StateDTO stateToStateDTO(State state);

    State stateDTOToState(StateDTO stateDTO);
    
    List<StateDTO> statesToStatesDTO(List<State> states);
    
    List<State> statesDTOToStates(List<StateDTO> statesDTO);

}
