package com.library.core.config.security.model;

import com.library.dao.model.entities.user.Role;
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
    private Role role;

    public TokenPayload(final Long userId, final Role role, final long exp) {
        this.userId = userId;
        this.exp = exp;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public long getExp() {
        return exp;
    }

    public Role getRole() {
        return role;
    }
}
