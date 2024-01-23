package online.mokkoji.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecordingPropertyRequestDto {

    private String name;
    private Boolean hasAudio;
    private Boolean hasVideo;
    private String outputMode;
    private String recordingLayout;
    private String resolution;
    private Number frameRate;
    private Number shmSize;

}
