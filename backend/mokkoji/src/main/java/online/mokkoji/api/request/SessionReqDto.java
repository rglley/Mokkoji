package online.mokkoji.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SessionReqDto {
    private Long userId;
    private Boolean allowTranscoding;
    private RecordingPropertyDto defaultRecordingProperties;
}
