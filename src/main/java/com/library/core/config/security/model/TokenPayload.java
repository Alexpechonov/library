package com.library.core.config.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ikatlinsky
 * @since 5/12/17
 */
@NoArgsConstructor
public class TokenPayload {
    private Long userId;
    private long exp;

    public TokenPayload(final Long userId, final long exp) {
        this.userId = userId;
        this.exp = exp;
    }

    public Long getUserId() {
        return userId;
    }

    public long getExp() {
        return exp;
    }
}
