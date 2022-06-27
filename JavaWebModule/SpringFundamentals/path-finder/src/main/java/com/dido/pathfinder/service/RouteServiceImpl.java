package com.dido.pathfinder.service;

import com.dido.pathfinder.model.entity.RouteEntity;
import com.dido.pathfinder.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService{

    private final RouteRepository routeRepository;

    @Override
    public List<RouteEntity> getMostCommented() {
        return routeRepository.findAll().stream()
                .sorted((routeOne, routeTwo) ->
                        Integer.compare(routeTwo.getComments().size(),
                                routeOne.getComments().size())
                ).limit(1).collect(Collectors.toList());
    }
}
