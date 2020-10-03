package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BuildingsRestController {

    @Autowired
    private BuildingsService buildingsService;

    public void setBuildingsService(BuildingsService buildingsService) {
        this.buildingsService = buildingsService;
    }

    @GetMapping("/api/buildings")
    public List<Building> getBuildings() {
        List<Building> buildings = buildingsService.retrieveBuildings();
        return buildings;
    }

    @GetMapping("/api/buildings/owner")
    public String retrieveOwner(@RequestParam(value = "owner") String owner) {
        return buildingsService.retrieveOwner(owner);
    }

    @GetMapping("/api/buildings/{buildingId}")
    public Building getBuilding(@PathVariable(name = "buildingId") Long buildingId) {
        return buildingsService.getBuilding(buildingId);
    }

    @PostMapping("/api/buildings")
    public String saveBuilding(Building building) {
        buildingsService.saveBuilding(building);
        return "Building Saved Successfully to H2 database, which can be found at http://localhost:8080/h2";
    }

    @DeleteMapping("/api/buildings/{buildingId}")
    public String deleteBuilding(@PathVariable(name = "buildingId") Long buildingId) {
        buildingsService.deleteBuilding(buildingId);
        return "Building Deleted Successfully from H2 database, which can be found at http://localhost:8080/h2";
    }

    @PutMapping("/api/buildings/{buildingId}")
    public void updateBuilding(@RequestBody Building building, @PathVariable(name = "buildingId") Long buildingId) {
        Building bld = buildingsService.getBuilding(buildingId);
        if (bld != null) {
            buildingsService.updateBuilding(building);
        }
    }

    @PutMapping("/api/buildings/taxes/{buildingId}")
    public String updateTaxes(Building building, @PathVariable(name = "buildingId") Long buildingId) {
        Building bld = buildingsService.getBuilding(buildingId);
        Double Taxes;
        if (bld != null) {
            if (bld.getValue() >= 220000.00 && bld.getValue() < 300000.00) {
                Taxes = bld.getValue() * 0.005;
                Taxes = Math.round(Taxes * 100.0) / 100.0;
                bld.setTax(Taxes);
                buildingsService.updateTaxes(bld);
                return "Building, which ID is: " + buildingId + " tax parameter successfully updated";
            } else if (bld.getValue() >= 300000.00 && bld.getValue() < 500000.00) {
                Taxes = bld.getValue() * 0.01;
                Taxes = Math.round(Taxes * 100.0) / 100.0;
                bld.setTax(Taxes);
                buildingsService.updateTaxes(bld);
                return "Building, which ID is: " + buildingId + " tax parameter successfully updated";
            } else if (bld.getValue() >= 500000.00) {
                Taxes = bld.getValue() * 0.02;
                Taxes = Math.round(Taxes * 100.0) / 100.0;
                bld.setTax(Taxes);
                buildingsService.updateTaxes(bld);
                return "Building, which ID is: " + buildingId + " tax parameter successfully updated";
            } else {
                buildingsService.updateTaxes(bld);
                return "Building, which ID is: " + buildingId + " tax parameter successfully updated";
            }
        }
        return null;
    }
}

