package com.example.demo;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class BuildingServiceTest {

    @Mock
    BuildingsRepository buildingsRepository;

    @InjectMocks
    BuildingsServiceImpl buildingsService;

    @Test
    public void getBuildingTest() {
        final Long id = 1L;
        final Building bld = new Building(1L, "Vilnius, Naujoji gatve 26", "Vilius Stankunas", 152.69,
                258742.46, "house", 0.0);
        when(buildingsRepository.findById(id)).thenReturn(Optional.of(bld));
        final Building expected = buildingsService.getBuilding(id);
        assertNotNull(expected);
    }

    @Test
    public void getBuildingsTest() {
        List<Building> buildings = new ArrayList<>();
        buildings.add(new Building(1L, "Vilnius, Naujoji gatve 26", "Vilius Stankunas", 152.69,
                258742.46, "house", 0.0));
        buildings.add(new Building(2L, "Vilnius, Naujoji gatve 28", "Vilius Stankunas", 152.69,
                458742.46, "house", 0.0));
        buildings.add(new Building(3L, "Vilnius, Naujoji gatve 16", "Vilius Stankunas", 152.69,
                358742.46, "house", 0.0));
        when(buildingsRepository.findAll()).thenReturn(buildings);
        List<Building> expected = buildingsService.retrieveBuildings();
        assertEquals(expected, buildings);
    }

    @Test
    public void saveBuildingTest() {
        final Building building1 = new Building(1L, "Vilnius, Naujoji gatve 26", "Vilius Stankunas", 152.69,
                258742.46, "house", 0.0);
        when(buildingsRepository.save(building1)).thenReturn(building1);
        buildingsRepository.save(building1);
        verify(buildingsRepository,times(1)).save(building1);
    }

    @Test
    public void saveBuildingReturnTest(){
        final Building building1 = new Building(1L, "Vilnius, Naujoji gatve 26", "Vilius Stankunas", 152.69,
                258742.46, "house", 0.0);
        String buildingSaved = buildingsService.saveBuilding(building1);
        String message = "Building Saved Successfully to H2 database, which can be found at http://localhost:8080/h2";
        assertEquals(buildingSaved,message);
    }

    @Test
    public void deleteBuildingTest(){
        final Long id = 1L;
        buildingsRepository.deleteById(id);
        buildingsRepository.deleteById(id);
        verify(buildingsRepository,times(2)).deleteById(id);
    }

    @Test
    public void deleteBuildingReturnTest() {
        final Building building1 = new Building(1L, "Vilnius, Naujoji gatve 26", "Vilius Stankunas", 152.69,
                258742.46, "house", 0.0);
        String buildingDeleted = buildingsService.deleteBuilding(building1.getId());
        String message = "Building Deleted Successfully from H2 database, which can be found at http://localhost:8080/h2";
        assertEquals(buildingDeleted, message);
    }

    @Test
    public void updateBuildingTest(){
        final Building building1 = new Building(1L, "Vilnius, Naujoji gatve 26", "Vilius Stankunas", 152.69,
                258742.46, "house", 0.0);
        given(buildingsRepository.save(building1)).willReturn(building1);
        final Building expected = buildingsService.updateBuilding(building1);
        assertNotNull(expected);
        verify(buildingsRepository).save(any(Building.class));
    }
}