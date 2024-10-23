package com.A_Olumide.Implementation.of.Service.Classes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String statusCode;

    private String statusMsg;
}
