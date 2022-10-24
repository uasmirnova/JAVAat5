package ru.netology.testinfo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class Info {
    private final String city;
    private final String name;
    private final String phone;
}
