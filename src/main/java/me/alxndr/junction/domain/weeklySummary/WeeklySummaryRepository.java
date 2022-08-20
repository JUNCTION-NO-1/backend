package me.alxndr.junction.domain.weeklySummary;

import java.time.LocalDate;
import me.alxndr.junction.domain.dinosaur.Dinosaur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklySummaryRepository extends JpaRepository<WeeklySummary, Long> {

	WeeklySummary findByDinosaurAndYearMonthAndWeek(Dinosaur dinosaur, LocalDate now, int weekOfMonth);
}
