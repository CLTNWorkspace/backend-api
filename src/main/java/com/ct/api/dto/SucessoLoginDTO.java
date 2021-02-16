package com.ct.api.dto;

import lombok.Data;

@Data
public class SucessoLoginDTO {

	private String token;

	private String refreshToken = "refresh";
}
