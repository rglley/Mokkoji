package online.mokkoji.api.request;

import io.openvidu.java.client.SessionProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {
    private Long userId;
    private Boolean allowTranscoding;
    private RecordingPropertyDto defaultRecordingProperties;
}
