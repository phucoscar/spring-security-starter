package com.phuckim.springsecuritystarter.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Phuckim
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {

    MALE(1), FEMALE(2);

    private final int value;
}
