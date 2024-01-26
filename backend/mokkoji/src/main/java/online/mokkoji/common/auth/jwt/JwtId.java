package online.mokkoji.common.auth.jwt;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import online.mokkoji.db.entity.Provider;

import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class JwtId implements Serializable {
    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String email;
}
