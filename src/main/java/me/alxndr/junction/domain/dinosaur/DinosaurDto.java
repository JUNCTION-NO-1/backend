package me.alxndr.junction.domain.dinosaur;

import java.util.Objects;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import me.alxndr.junction.domain.weeklySummary.WeeklySummary;

public class DinosaurDto {

	@Getter
	@Builder
	@ToString
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

	@Getter
	@Builder
	public static class RankingResponse {
		private Integer ranking;

		private String nickname;

		private Long totalTime; // minute

		private Integer level;

		public static RankingResponse of(int ranking, WeeklySummary weeklySummary) {
			return RankingResponse.builder()
					.ranking(ranking)
					.nickname(weeklySummary.getDinosaur().getNickName())
					.totalTime(weeklySummary.getTotalTime())
					.level(weeklySummary.getLevel())
					.build();
		}

	}


}
