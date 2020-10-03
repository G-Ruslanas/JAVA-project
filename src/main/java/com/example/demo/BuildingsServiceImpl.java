package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BuildingsServiceImpl implements BuildingsService {

    @Autowired
    private BuildingsRepository buildingsRepository;

    public void setBuildingsRepository(BuildingsRepository buildingsRepository) {
        this.buildingsRepository = buildingsRepository;
    }

    public List<Building> retrieveBuildings(){
        List<Building> buildings = buildingsRepository.findAll();
        return buildings;
    }

    public String retrieveOwner(String owner){
        List<Building> buildings = buildingsRepository.findAll();
        Double fullValue = 0.0;
        for(Building temp:buildings){
            if(temp.getOwner().equals(owner)){
                fullValue+=temp.getTax();
            }
        fullValue=Math.round(fullValue*100.0)/100.0;
        }
        return "Owner is: " + owner + " and total buildings taxes are:"+ fullValue;
    }

    public Building getBuilding(Long buildingId){
        Optional<Building> optBld = buildingsRepository.findById(buildingId);
        return optBld.get();
    }

    public String saveBuilding(Building building){
        buildingsRepository.save(building);
        return "Building Saved Successfully to H2 database, which can be found at http://localhost:8080/h2";
    }

    public String deleteBuilding(Long buildingId){
        buildingsRepository.deleteById(buildingId);
        return "Building Deleted Successfully from H2 database, which can be found at http://localhost:8080/h2";
    }

    public Building updateBuilding(Building building){ buildingsRepository.save(building);
        return building;
    }

    public String updateTaxes(Building building){ buildingsRepository.save(building);
        return "Building, which ID is: " +  building.getId() + " tax parameter successfully updated";
    }


}
