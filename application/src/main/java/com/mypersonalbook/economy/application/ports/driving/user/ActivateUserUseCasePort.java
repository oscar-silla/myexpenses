package com.mypersonalbook.economy.application.ports.driving.user;

import com.mypersonalbook.economy.domain.EmailCode;

public interface ActivateUserUseCasePort {
    void execute(EmailCode emailCode);
}
