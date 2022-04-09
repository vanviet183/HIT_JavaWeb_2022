package com.vitvn183.exam1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DarlingDto {

    @Nationalized
    private String name;

    private String phone;

    private String email;

    private String favorite;

    private Integer status;
}
