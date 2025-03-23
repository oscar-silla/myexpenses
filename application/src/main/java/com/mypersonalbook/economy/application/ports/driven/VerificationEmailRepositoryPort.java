package com.mypersonalbook.economy.application.ports.driven;

import java.util.UUID;

public interface VerificationEmailRepositoryPort {
    boolean save(String email, UUID uuid);
}
