package com.api.service;

import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }


    public List<RegistrationDto> getRegistration(){
        List<Registration> registrations=registrationRepository.findAll();
        List<RegistrationDto> dtos = registrations.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return dtos;
    }


    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        //copy dto to entity
//        Registration registration = new Registration();
//        registration.setName(registrationDto.getName());
//        registration.setEmail(registrationDto.getEmail());
//        registration.setMobile(registrationDto.getMobile());
        Registration registration = mapToEntity(registrationDto);

        Registration savedEntity = registrationRepository.save(registration);

        //copy entity to dto
//        RegistrationDto dto = new RegistrationDto();
//        dto.setName(savedEntity.getName());
//        dto.setEmail(savedEntity.getEmail());
//        dto.setMobile(savedEntity.getMobile());
        RegistrationDto dto  = mapToDto(registration);
        return dto;
    }

    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    public Registration updateRegistrationMethod(long id, Registration registration) {
        Registration registration1 = registrationRepository.findById(id).get();
        registration1.setId(id);
        registration1.setName(registration.getName());
        registration1.setEmail(registration.getEmail());
        registration1.setMobile(registration.getMobile());
        Registration savedEntity = registrationRepository.save(registration1);
        return savedEntity;
    }

    Registration mapToEntity(RegistrationDto registrationDto){
        Registration Entity = modelMapper.map(registrationDto, Registration.class);
//        Registration registration = new Registration();
//        registration.setName(registrationDto.getName());
//        registration.setEmail(registrationDto.getEmail());
//        registration.setMobile(registrationDto.getMobile());
        return Entity;
    }

    RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto = modelMapper.map(registration,RegistrationDto.class);
//        RegistrationDto dto = new RegistrationDto();
//        dto.setName(registration.getName());
//        dto.setEmail(registration.getEmail());
//        dto.setMobile(registration.getMobile());
        return dto;
    }

    public RegistrationDto getRegistrationId(long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(
                        ()->new ResourceNotFoundException("Record Not Found"));
        return mapToDto(registration);
    }


}
