package com.example.StudentManagementSystem.core;

import com.example.StudentManagementSystem.entity.User;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

public interface BaseMapper<E , REQ, RES> {
    @Named("requestToEntity")
    E requestToEntity(REQ request);

    @Named("responseToEntity")
    E responseToEntity(RES response);

    @Named("entityToRequest")
    REQ entityToRequest(E entity);

    @Named("entityToResponse")
    RES entityToResponse(E entity);

    @Named("entityToListResponse")
    List<RES> entityToListResponse(List<E> entity);

    void update(@MappingTarget E targetEntity, REQ updatingRequest);
}