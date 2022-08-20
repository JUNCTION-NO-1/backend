package me.alxndr.junction.domain.weeklySummary;

import java.time.LocalDate;
import java.util.List;
import me.alxndr.junction.domain.dinosaur.Dinosaur;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklySummaryRepository extends JpaRepository<WeeklySummary, Long> {

	WeeklySummary findByDinosaurAndYearMonthAndWeek(Dinosaur dinosaur, LocalDate now, int weekOfMonth);


	@Query(value = "select ws from WeeklySummary ws inner join fetch ws.dinosaur where ws.yearMonth = :now and ws.week = :week order by ws.totalTime desc")
	List<WeeklySummary> getRanking(@Param(value = "now") LocalDate now, @Param(value = "week") int weekOfMonth, final PageRequest of);

}
