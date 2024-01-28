package online.mokkoji.api.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class MyPageDto {

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
}
