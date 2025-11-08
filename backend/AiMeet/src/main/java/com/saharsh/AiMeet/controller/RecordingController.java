@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RecordingController {

    private final RecordingService recordingService;

    @PostMapping("/start-recording")
    public Mono<ResponseEntity<Map>> startRecording(@RequestBody Map<String, String> req) {
        String roomId = req.get("roomId");
        return recordingService.startRecording(roomId)
                .map(ResponseEntity::ok);
    }
}
