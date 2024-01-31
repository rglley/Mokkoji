package online.mokkoji.result.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    MEMORY("memory"), RECOLLECTION("recollection");

    private final String key;
}
