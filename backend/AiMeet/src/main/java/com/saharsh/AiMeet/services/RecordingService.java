@Service
@RequiredArgsConstructor
public class RecordingService {

    private final WebClient webClient;

    @Value("${zego.appId}")
    private long appId;

    @Value("${zego.serverSecret}")
    private String serverSecret;

    @Value("${zego.baseUrl}")
    private String zegoBaseUrl;

    public Mono<Map> startRecording(String roomId) {
        String url = zegoBaseUrl + "/" + appId + "/cloud_recording/start";

        Map<String, Object> payload = Map.of(
            "app_id", appId,
            "room_id", roomId,
            "task_id", "record_" + System.currentTimeMillis(),
            "record_mode", 2,         // 2 = Audio Only
            "recording_file_type", "AAC" // Output audio format
        );

        return webClient.post()
                .uri(url)
                .header("AppId", String.valueOf(appId))
                .header("ServerSecret", serverSecret)
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(Map.class);
    }
}
