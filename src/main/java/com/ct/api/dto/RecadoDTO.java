package com.ct.api.dto;

import lombok.Data;

@Data
public class RecadoDTO {

	public Long autor;

	public String data;

	public String menssagem;

	public boolean like;

	public boolean dislike;
}
