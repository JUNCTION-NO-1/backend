package me.alxndr.junction.domain.dinosaur;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.alxndr.junction.domain.dinosaur.DinosaurDto.DinosaurRequest;
import me.alxndr.junction.domain.dinosaur.DinosaurDto.DinosaurResponse;
import me.alxndr.junction.domain.dinosaur.DinosaurDto.TimeUpRequest;
import me.alxndr.junction.domain.dinosaur.DinosaurDto.TimeUpResponse;
import me.alxndr.junction.domain.weeklySummary.WeeklySummary;
import me.alxndr.junction.domain.weeklySummary.WeeklySummaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DinosaurService {

	private final Integer levelTime;

	private final DinosaurRepository dinosaurRepository;

	private final WeeklySummaryRepository weeklySummaryRepository;

	@Transactional
	public DinosaurResponse getDinosaur(DinosaurRequest req) {

		String id = req.getId();

		final Optional<Dinosaur> optionalDinosaur = getDinosaur(id);
		final var now = LocalDate.now();
		final int weekOfMonth = getWeekOfMonth();

		if (optionalDinosaur.isPresent()) {
			final var dinosaur = optionalDinosaur.get();

			final WeeklySummary weeklySummary = getWeeklySummary(now, weekOfMonth, dinosaur);

			return DinosaurResponse.of(dinosaur, weeklySummary);

		} else {

			final var dinosaur = Dinosaur.create(req);
			dinosaurRepository.save(dinosaur);

			final var weeklySummary = WeeklySummary.create(dinosaur, weekOfMonth);
			weeklySummaryRepository.save(weeklySummary);

			return DinosaurResponse.of(dinosaur, weeklySummary);

		}
	}

	@Transactional
	public TimeUpResponse timeUp(TimeUpRequest req) {

		final var optionalDinosaur = getDinosaur(req.getId());

		final var dinosaur = optionalDinosaur.get();
		final var weekOfMonth = getWeekOfMonth();

		final var weeklySummary = getWeeklySummary(LocalDate.now(), weekOfMonth, dinosaur);
		weeklySummary.calculateTime(req.getMinute(), levelTime);

		return TimeUpResponse.of(weeklySummary);
	}

	private WeeklySummary getWeeklySummary(final LocalDate now, final int weekOfMonth, final Dinosaur dinosaur) {
		final var weeklySummary = weeklySummaryRepository.findByDinosaurAndYearMonthAndWeek(dinosaur, now, weekOfMonth);
		return weeklySummary;
	}

	private Optional<Dinosaur> getDinosaur(final String id) {
		final var optionalDinosaur = dinosaurRepository.findById(id);
		return optionalDinosaur;
	}

	private int getWeekOfMonth() {
		LocalDate now = LocalDate.now();
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		final var weekOfMonth = now.get(weekFields.weekOfWeekBasedYear());
		return weekOfMonth;
	}


}
