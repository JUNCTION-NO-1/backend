package me.alxndr.junction.domain.dinosaur;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.alxndr.junction.domain.dinosaur.DinosaurDto.DinosaurRequest;
import me.alxndr.junction.domain.weeklySummary.WeeklySummary;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dinosaur {

	@Id
	private String id;

	private String nickName;

	@Builder.Default
	@OneToMany(mappedBy = "dinosaur")
	private List<WeeklySummary> weeklySummaries = new ArrayList<>();


	public static Dinosaur create(DinosaurRequest req) {
		return Dinosaur.builder()
				.id(req.getId())
				.nickName(req.getNickname())
				.build();
	}

}
