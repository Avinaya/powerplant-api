package com.proshore.powerplant.util;

import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransferObject {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <T, D> List<T> mapToList(List<D> objects, Class<T> toClass) {
        return Optional.ofNullable(objects).stream().flatMap(Collection::stream)
                .map(o ->
                        modelMapper.map(o, toClass)
                ).collect(Collectors.toList());
    }

    public static <T, D> T map(D object, Class<T> toClass) {
        return Optional.ofNullable(object)
                .map(o -> modelMapper.map(o, toClass))
                .orElse(null);
    }
}
