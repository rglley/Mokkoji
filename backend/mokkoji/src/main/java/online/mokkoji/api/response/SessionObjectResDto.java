package online.mokkoji.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import online.mokkoji.api.request.RecordingPropertyReqDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SessionObjectResDto {

    private String id;
    private String object;
    private Number createdAt; //millisecond
    private String mediaMode;
    private String recordingMode;
    private RecordingPropertyReqDto defaultRecordingProperties;
    private ConnectionsResDto connections;
    private Boolean allowTranscoding;

    public SessionObjectResDto(String id, RecordingPropertyReqDto defaultRecordingProperties) {
        this.id = id;
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
