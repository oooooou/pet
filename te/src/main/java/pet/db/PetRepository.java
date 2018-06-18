package pet.db;

import pet.pojo.Pet;

import java.util.List;

public interface PetRepository {
    Pet findByNameAndMasterID(String name, int masterId);

    List<Pet> findByMasterID(int masterId);

    void addPet(Pet pet);

    Long countPetByNameAndMaId(String name,Long masterId);

    Long deletePetByMasterIdAndId(Long masterId,Long id);
}