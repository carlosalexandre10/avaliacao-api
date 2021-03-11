package br.com.cast.avaliacao.dtos;

import org.modelmapper.ModelMapper;

import br.com.cast.avaliacao.model.Categoria;
import lombok.Data;

@Data
public class CategoriaDTO {
    private Long codigo;
    private String descricao;
    
    public static CategoriaDTO create(Categoria categoria) {
    	ModelMapper modelMapper = new ModelMapper();
    	return modelMapper.map(categoria, CategoriaDTO.class);
    }
}
