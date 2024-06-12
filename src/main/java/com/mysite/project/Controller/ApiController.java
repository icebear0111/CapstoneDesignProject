package com.mysite.project.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysite.project.DTO.AccidentDto;
import com.mysite.project.DTO.CheckboxDto;
import com.mysite.project.Entity.AccidentEntity;
import com.mysite.project.Repository.AccidentRepository;
import com.mysite.project.Service.AccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final AtomicReference<CheckboxDto> lastCheckboxData = new AtomicReference<>(new CheckboxDto());
    private volatile String lastUploadedVideoFilename;

    @Autowired
    private AccidentRepository accidentRepository;

    @Autowired
    private AccidentService accidentService;

    @Value("${json.file.path}")
    private String jsonFilePath;

    @GetMapping("/test")
    public String testApi(){
        return "connection successful";
    }

    @PostMapping("/video-upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        try {
            String path = accidentService.processVideo(file);
            lastUploadedVideoFilename = file.getOriginalFilename();
            accidentService.sendVideoFile(path);
            return ResponseEntity.ok(path);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload video: " + e.getMessage());
        }
    }

    @PostMapping("/add-info")
    public ResponseEntity<String> addInfo(@RequestBody CheckboxDto checkboxDto) {
        lastCheckboxData.set(checkboxDto);

        return ResponseEntity.ok("Checkbox data received and processed.");
    }

    @GetMapping("/result-view")
    public ResponseEntity<?> getAccidentData() {
        String filePath = jsonFilePath + "/response.json";

        try {
            String jsonContent = Files.readString(Paths.get(filePath));
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonData = objectMapper.readValue(jsonContent, Map.class);
            Long accidentId = Long.parseLong(jsonData.get("accidentId").toString());

            Optional<AccidentEntity> entity = accidentRepository.findById(accidentId);
            if (entity.isPresent()) {
                AccidentEntity accidentEntity = entity.get();
                CheckboxDto checkboxDto = lastCheckboxData.get();
                Integer faultPercentageA = accidentEntity.getFaultPercentageA();
                Integer faultPercentageB = accidentEntity.getFaultPercentageB();

                if (checkboxDto.isCheckbox1()) { // A 10km/h 이상 20km/h 미만의 제한속도 위반
                    faultPercentageA += 10;
                    faultPercentageB -= 10;
                }
                if (checkboxDto.isCheckbox2()) { // A 20km/h 이상의 제한속도 위반
                    faultPercentageA += 20;
                    faultPercentageB -= 20;
                }
                if (checkboxDto.isCheckbox3()) { // A 일방통행 위반
                    faultPercentageA += 30;
                    faultPercentageB -= 30;
                }
                if (checkboxDto.isCheckbox4()) { // A 졸음운전
                    faultPercentageA += 20;
                    faultPercentageB -= 20;
                }
                if (checkboxDto.isCheckbox5()) { // A 혈중알코올농도 0.03% 미만 음주운전
                    faultPercentageA += 10;
                    faultPercentageB -= 10;
                }
                if (checkboxDto.isCheckbox6()) { // A 혈중알코올농도 0.03% 이상 음주운전
                    faultPercentageA += 20;
                    faultPercentageB -= 20;
                }
                if (checkboxDto.isCheckbox7()) { // A 마약 등의 약물운전
                    faultPercentageA += 20;
                    faultPercentageB -= 20;
                }
                if (checkboxDto.isCheckbox8()) { // A 무면허운전
                    faultPercentageA += 20;
                    faultPercentageB -= 20;
                }
                if (checkboxDto.isCheckbox9()) { // A 차량 유리의 암도가 높은 경우
                    faultPercentageA += 10;
                    faultPercentageB -= 10;
                }
                if (checkboxDto.isCheckbox10()) { // A 운전 중 휴대전화 사용
                    faultPercentageA += 10;
                    faultPercentageB -= 10;
                }
                if (checkboxDto.isCheckbox11()) { // A 운전 중 영상표시장치 시청·조작
                    faultPercentageA += 10;
                    faultPercentageB -= 10;
                }
                if (checkboxDto.isCheckbox12()) { // A 서행
                    faultPercentageA -= 10;
                    faultPercentageB += 10;
                }
                if (checkboxDto.isCheckbox13()) { // B 10km/h 이상 20km/h 미만의 제한속도 위반
                    faultPercentageA -= 10;
                    faultPercentageB += 10;
                }
                if (checkboxDto.isCheckbox14()) { // B 20km/h 이상의 제한속도 위반
                    faultPercentageA -= 20;
                    faultPercentageB += 20;
                }
                if (checkboxDto.isCheckbox15()) { // B 일방통행 위반
                    faultPercentageA -= 30;
                    faultPercentageB += 30;
                }
                if (checkboxDto.isCheckbox16()) { // B 졸음운전
                    faultPercentageA -= 20;
                    faultPercentageB += 20;
                }
                if (checkboxDto.isCheckbox17()) { // B 혈중알코올농도 0.03% 미만 음주운전
                    faultPercentageA -= 10;
                    faultPercentageB += 10;
                }
                if (checkboxDto.isCheckbox18()) { // B 혈중알코올농도 0.03% 이상 음주운전
                    faultPercentageA -= 20;
                    faultPercentageB += 20;
                }
                if (checkboxDto.isCheckbox19()) { // B 마약 등의 약물운전
                    faultPercentageA -= 20;
                    faultPercentageB += 20;
                }
                if (checkboxDto.isCheckbox20()) { // B 무면허운전
                    faultPercentageA -= 20;
                    faultPercentageB += 20;
                }
                if (checkboxDto.isCheckbox21()) { // B 차량 유리의 암도가 높은 경우
                    faultPercentageA -= 10;
                    faultPercentageB += 10;
                }
                if (checkboxDto.isCheckbox22()) { // B 운전 중 휴대전화 사용
                    faultPercentageA -= 10;
                    faultPercentageB += 10;
                }
                if (checkboxDto.isCheckbox23()) { // B 운전 중 영상표시장치 시청·조작
                    faultPercentageA -= 10;
                    faultPercentageB += 10;
                }
                if (checkboxDto.isCheckbox24()) { // B 서행
                    faultPercentageA += 10;
                    faultPercentageB -= 10;
                }

                faultPercentageA = Math.max(0, Math.min(100, faultPercentageA));
                faultPercentageB = Math.max(0, Math.min(100, faultPercentageB));

                if (lastUploadedVideoFilename != null && !lastUploadedVideoFilename.isEmpty()) {
                    accidentService.deleteVideo(lastUploadedVideoFilename);
                    lastUploadedVideoFilename = null;
                }

                int sleepTime = ThreadLocalRandom.current().nextInt(5000, 10001);
                Thread.sleep(sleepTime);

                return ResponseEntity.ok(new AccidentDto(
                        accidentEntity.getLocation(),
                        accidentEntity.getVehicleADirection(),
                        accidentEntity.getVehicleBDirection(),
                        faultPercentageA,
                        faultPercentageB,
                        accidentEntity.getAccidentDescription(),
                        accidentEntity.getPortalLink(),
                        accidentEntity.getVideoLink()
                ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No accident found for ID: " + accidentId);
            }
        } catch (IOException | NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}



/*
if (checkboxDto.isCheckbox1()) { // A 10km/h 이상 20km/h 미만의 제한속도 위반
    faultPercentageA += 10;
    faultPercentageB -= 10;
}
if (checkboxDto.isCheckbox2()) { // A 20km/h 이상의 제한속도 위반
    faultPercentageA += 20;
    faultPercentageB -= 20;
}
if (checkboxDto.isCheckbox3()) { // A 일방통행 위반
    faultPercentageA += 30;
    faultPercentageB -= 30;
}
if (checkboxDto.isCheckbox4()) { // A 졸음운전
    faultPercentageA += 20;
    faultPercentageB -= 20;
}
if (checkboxDto.isCheckbox5()) { // A 혈중알코올농도 0.03% 미만 음주운전
    faultPercentageA += 10;
    faultPercentageB -= 10;
}
if (checkboxDto.isCheckbox6()) { // A 혈중알코올농도 0.03% 이상 음주운전
    faultPercentageA += 20;
    faultPercentageB -= 20;
}
if (checkboxDto.isCheckbox7()) { // A 마약 등의 약물운전
    faultPercentageA += 20;
    faultPercentageB -= 20;
}
if (checkboxDto.isCheckbox8()) { // A 무면허운전
    faultPercentageA += 20;
    faultPercentageB -= 20;
}
if (checkboxDto.isCheckbox9()) { // A 차량 유리의 암도가 높은 경우
    faultPercentageA += 10;
    faultPercentageB -= 10;
}
if (checkboxDto.isCheckbox10()) { // A 운전 중 휴대전화 사용
    faultPercentageA += 10;
    faultPercentageB -= 10;
}
if (checkboxDto.isCheckbox11()) { // A 운전 중 영상표시장치 시청·조작
    faultPercentageA += 10;
    faultPercentageB -= 10;
}
if (checkboxDto.isCheckbox12()) { // A 서행
    faultPercentageA -= 10;
    faultPercentageB += 10;
}
if (checkboxDto.isCheckbox13()) { // B 10km/h 이상 20km/h 미만의 제한속도 위반
    faultPercentageA -= 10;
    faultPercentageB += 10;
}
if (checkboxDto.isCheckbox14()) { // B 20km/h 이상의 제한속도 위반
    faultPercentageA -= 20;
    faultPercentageB += 20;
}
if (checkboxDto.isCheckbox15()) { // B 일방통행 위반
    faultPercentageA -= 30;
    faultPercentageB += 30;
}
if (checkboxDto.isCheckbox16()) { // B 졸음운전
    faultPercentageA -= 20;
    faultPercentageB += 20;
}
if (checkboxDto.isCheckbox17()) { // B 혈중알코올농도 0.03% 미만 음주운전
    faultPercentageA -= 10;
    faultPercentageB += 10;
}
if (checkboxDto.isCheckbox18()) { // B 혈중알코올농도 0.03% 이상 음주운전
    faultPercentageA -= 20;
    faultPercentageB += 20;
}
if (checkboxDto.isCheckbox19()) { // B 마약 등의 약물운전
    faultPercentageA -= 20;
    faultPercentageB += 20;
}
if (checkboxDto.isCheckbox20()) { // B 무면허운전
    faultPercentageA -= 20;
    faultPercentageB += 20;
}
if (checkboxDto.isCheckbox21()) { // B 차량 유리의 암도가 높은 경우
    faultPercentageA -= 10;
    faultPercentageB += 10;
}
if (checkboxDto.isCheckbox22()) { // B 운전 중 휴대전화 사용
    faultPercentageA -= 10;
    faultPercentageB += 10;
}
if (checkboxDto.isCheckbox23()) { // B 운전 중 영상표시장치 시청·조작
    faultPercentageA -= 10;
    faultPercentageB += 10;
}
if (checkboxDto.isCheckbox24()) { // B 서행
    faultPercentageA += 10;
    faultPercentageB -= 10;
}
 */