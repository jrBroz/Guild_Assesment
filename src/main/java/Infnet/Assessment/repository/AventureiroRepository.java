// AventureiroRepository.java
package Infnet.Assessment.repository;
import Infnet.Assessment.model.Aventureiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AventureiroRepository extends JpaRepository<Aventureiro, Long> { }

