package me.alxndr.junction.domain.dinosaur;

import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.alxndr.junction.domain.weeklySummary.WeeklySummary;
import org.springframework.context.annotation.Bean;

public class DinosaurDto {

	@Getter
	@Builder
	public static class DinosaurRequest {
		private String id;

		private String nickname;

		public String getId() {
			if (Objects.isNull(this.id)) {
				return UUID.randomUUID().toString();
			} else {
				return this.id;
			}
		}
	}

	@Getter
	@Builder
	public static class DinosaurResponse {
		private String id;

		private Integer level;

		private Long totalTime;

		public static DinosaurResponse of(Dinosaur dinosaur, WeeklySummary weeklySummary) {
			return DinosaurResponse.builder()
					.id(dinosaur.getId())
					.level(weeklySummary.getLevel())
					.totalTime(weeklySummary.getTotalTime())
					.build();
		}

	}


	@Getter
	@Builder
	public static class TimeUpRequest {

		private String id;

		private Long minute;
	}


	@Getter
	@Builder
	public static class TimeUpResponse {
		private Long totalTime;

		private Integer level;

		public static TimeUpResponse of(WeeklySummary weeklySummary) {
			return TimeUpResponse.builder()
					.totalTime(weeklySummary.getTotalTime())
					.level(weeklySummary.getLevel())
					.build();
		}
	}


}
