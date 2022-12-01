package Musaib.MybackendProject.Repositories;

import Musaib.MybackendProject.Models.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageDataRep extends JpaRepository<ImageData,Integer> {

    Optional<ImageData> findByImageName(String imageName);
}
