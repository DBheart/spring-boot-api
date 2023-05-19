package kr.deity.springboot2_x.lecture;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kr.deity.springboot2_x.lecture.entity.Lecture;
import kr.deity.springboot2_x.web.BaseResponse;
import kr.deity.springboot2_x.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lecture")
@RequiredArgsConstructor
public class LectureController {

    final LectureService lectureService;


    @GetMapping("/list")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "오류가 발생하였습니다.", content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    public DataResponse<List<Lecture>> listLecture(){

        return new DataResponse<>(lectureService.list());
    }

    @PostMapping("/saveSpringBootRquestParam")
    public void saveSpringBoot2(@RequestParam String name) {
        Lecture lecture = new Lecture();
        lecture.setName(name);

        lectureService.save(lecture);

    }

    @PostMapping("/saveSpringBootRquestDto")
    public void saveSpringBootRquestDto(@RequestBody Lecture lecture) {
//        Lecture lecture = new Lecture();
//        lecture.setName(name);

        lectureService.save(lecture);

    }
}
