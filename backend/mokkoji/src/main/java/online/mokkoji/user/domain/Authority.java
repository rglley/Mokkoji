package online.mokkoji.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority {
    GUEST("GUEST"), USER("USER");

    private final String key;
}
