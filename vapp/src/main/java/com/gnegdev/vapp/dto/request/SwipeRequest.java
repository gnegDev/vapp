package com.gnegdev.vapp.dto.request;

import jakarta.validation.constraints.NotNull;

public record SwipeRequest(@NotNull SwipeAction action, @NotNull Long roleId) {}
