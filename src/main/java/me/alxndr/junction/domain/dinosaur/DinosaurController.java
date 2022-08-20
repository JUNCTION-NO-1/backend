package me.alxndr.junction.domain.dinosaur;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.alxndr.junction.domain.dinosaur.DinosaurDto.DinosaurRequest;
import me.alxndr.junction.domain.dinosaur.DinosaurDto.TimeUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "공룡 API")
@RequestMapping("/dinosaur")
public class DinosaurController {

	private final DinosaurService dinosaurService;

	@ApiOperation(value = "공룡 정보 (저장된게 없다면 저장)")
	@PostMapping
	public ResponseEntity getDinosaur(@RequestBody DinosaurRequest req, HttpServletRequest request) {

		final var dinosaur = dinosaurService.getDinosaur(req);

		final var headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			log.error(request.getHeader(headerNames.nextElement()));
		}

		return ResponseEntity.ok(dinosaur);
	}

	@ApiOperation(value = "타이머 종료")
	@PostMapping("/quit")
	public ResponseEntity levelup(@RequestBody TimeUpRequest req) {

		final var timeUpResponse = dinosaurService.timeUp(req);

		return ResponseEntity.ok(timeUpResponse);
	}

	// ranking
	@ApiOperation(value = "랭킹")
	@GetMapping("/ranking")
	public ResponseEntity getRanking(DinosaurRequest req) {

		final var ranking = dinosaurService.getRanking(req);

		return ResponseEntity.ok(ranking);
	}
}
