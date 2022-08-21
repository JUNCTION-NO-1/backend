package me.alxndr.junction.domain.weeklySummary;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.alxndr.junction.domain.dinosaur.Dinosaur;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class WeeklySummary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate yearMonth;

	private Integer week;

	private Long totalTime;

	private Integer level;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dinosaur_id")
	private Dinosaur dinosaur;

	public static WeeklySummary create(Dinosaur dinosaur, Integer week) {
		return WeeklySummary
				.builder()
				.yearMonth(LocalDate.now())
				.week(week)
				.dinosaur(dinosaur)
				.totalTime(0L)
				.level(1)
				.build();
	}

	public void calculateTime(Long minute, int levelTime) {
		this.totalTime += minute;

		final int l = (int) (this.totalTime / levelTime);

		log.error("@@@@ {}", l);
		final Integer l1 = l - level;

		if (this.level < 7) {
			this.level += l1;
		}

		if (this.level > 7) {
			this.level = 7;
		}
	}

}
