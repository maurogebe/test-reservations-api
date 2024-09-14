package com.example.demo.application.usecases;

import com.example.demo.application.dtos.StateDTO;
import com.example.demo.application.exeptions.ApiRequestException;
import com.example.demo.application.mappers.StateMapper;
import com.example.demo.domain.entities.State;
import com.example.demo.domain.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateUseCase {

    private final StateRepository stateRepository;

    @Autowired
    public StateUseCase(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public StateDTO getStateById(Long id) {
        Optional<State> stateById = this.stateRepository.findById(id);

        if(stateById.isEmpty()) throw new ApiRequestException("No se encontró el estado con ID: " + id, HttpStatus.NOT_FOUND);

        return StateMapper.INSTANCE.stateToStateDTO(stateById.get());
    }
    
    public List<StateDTO> getStates() {
        List<State> states = this.stateRepository.findAll();
        return StateMapper.INSTANCE.statesToStatesDTO(states);
    }

    public void createState(StateDTO state) {
        this.stateRepository.save(StateMapper.INSTANCE.stateDTOToState(state));
    }
}
