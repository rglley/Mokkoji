package online.mokkoji.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import online.mokkoji.api.request.RecordingPropertyDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SessionObjectResDto {

    private Long userId;
    private String id;
    private String object;
    private Number createdAt; //millisecond
    private String mediaMode;
    private String recordingMode;
    private RecordingPropertyDto defaultRecordingProperties;
    private ConnectionsResDto connections;
    private Boolean allowTranscoding;

    public SessionObjectResDto(Long userId, String sessionId, RecordingPropertyDto defaultRecordingProperties) {
        this.userId = userId;
        this.id = sessionId;
        this.defaultRecordingProperties = defaultRecordingProperties;
        this.createdAt = System.currentTimeMillis();
        this.object = "session";
        this.mediaMode = "ROUTED";
        this.recordingMode = "MANUAL";

        //Connection 받아오기
        this.connections = new ConnectionsResDto(1);
        this.allowTranscoding = true;
    }

}
