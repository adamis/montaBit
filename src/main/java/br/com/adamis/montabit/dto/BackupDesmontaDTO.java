package br.com.adamis.montabit.dto;

import java.util.List;

import lombok.Data;

@Data
public class BackupDesmontaDTO {
	
	private String bitString;
	private List<BitsDTO> listBits;
	
}
