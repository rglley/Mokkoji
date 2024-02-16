package online.mokkoji.user.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
public class MyPageResDto {

    @NotBlank
    private String image;

    @NotBlank
    private String name;

    @NotNull
    private boolean isAccountRegistered;

    @NotBlank
    private int eventCount;

    @NotBlank
    private int totalTime;

    @NotBlank
    private int totalParticipant;

    @NotBlank
    private int totalMessage;

    @Builder
    public MyPageResDto (String image, String name, boolean isAccountRegistered,
                         int eventCount, int totalTime, int totalParticipant, int totalMessage) {
        this.image = image;
        this.name = name;
        this.isAccountRegistered = isAccountRegistered;
        this.eventCount = eventCount;
        this.totalTime = totalTime;
        this.totalParticipant = totalParticipant;
        this.totalMessage = totalMessage;
    }
}
