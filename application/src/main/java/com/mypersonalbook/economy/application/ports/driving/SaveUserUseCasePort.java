package com.mypersonalbook.economy.application.ports.driving;

import com.mypersonalbook.economy.domain.User;

public interface SaveUserUseCasePort {
    void execute(User user);
}
