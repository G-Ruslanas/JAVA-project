package com.example.demo;


import java.util.List;

public interface BuildingsService {

    public List<Building> retrieveBuildings();

    public Building getBuilding(Long buildingId);

    public String saveBuilding(Building building);

    public String deleteBuilding(Long buildingId);

    public Building updateBuilding(Building building);

    public String retrieveOwner(String owner);

    public String updateTaxes(Building building);
}
