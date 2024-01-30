package online.mokkoji.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Provider {
    google("google"), naver("naver");

    private final String key;
}
