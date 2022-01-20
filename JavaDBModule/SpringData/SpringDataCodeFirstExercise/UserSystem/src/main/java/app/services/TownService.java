package app.services;

import app.models.Town;

import java.util.List;

public interface TownService {

    void persist(Town town);

    List<Town> findTownByName(String name);
}
