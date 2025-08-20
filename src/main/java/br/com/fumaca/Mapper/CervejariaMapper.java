package br.com.fumaca.Mapper;

import br.com.fumaca.dto.CervejariaRequestDTO;
import br.com.fumaca.dto.CervejariaResponseDTO;
import br.com.fumaca.model.Cervejaria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CervejariaMapper {

        @Mappings({
                @Mapping(target = "razaoSocial", source = "razaoSocial"),
                @Mapping(target = "nomeFantasia", source = "nomeFantasia"),
                @Mapping(target = "cnpj", source = "cnpj"),
                @Mapping(target = "responsavel", source = "responsavel"),
                @Mapping(target = "telefone", source = "telefone"),
                @Mapping(target = "email", source = "email"),
                @Mapping(target = "comissaoMarketplace", source = "comissaoMarketplace"),
                @Mapping(target = "raioEntrega", source = "raioEntrega"),
                @Mapping(target = "taxaEntrega", source = "taxaEntrega"),
                @Mapping(target = "tempoEntrega", source = "tempoEntrega"),
                @Mapping(target = "ativo", source = "ativo"),

                // Endereço embutido
                @Mapping(target = "endereco.cep", source = "cep"),
                @Mapping(target = "endereco.logradouro", source = "logradouro"),
                @Mapping(target = "endereco.numero", source = "numero"),
                @Mapping(target = "endereco.complemento", source = "complemento"),
                @Mapping(target = "endereco.bairro", source = "bairro"),
                @Mapping(target = "endereco.cidade", source = "cidade"),
                @Mapping(target = "endereco.estado", source = "estado")
        })
        Cervejaria toEntity(CervejariaRequestDTO dto);

        @Mappings({
                @Mapping(target = "id", source = "id"),
                @Mapping(target = "razaoSocial", source = "razaoSocial"),
                @Mapping(target = "nomeFantasia", source = "nomeFantasia"),
                @Mapping(target = "cnpj", source = "cnpj"),
                @Mapping(target = "responsavel", source = "responsavel"),
                @Mapping(target = "telefone", source = "telefone"),
                @Mapping(target = "email", source = "email"),
                @Mapping(target = "comissaoMarketplace", source = "comissaoMarketplace"),
                @Mapping(target = "raioEntrega", source = "raioEntrega"),
                @Mapping(target = "taxaEntrega", source = "taxaEntrega"),
                @Mapping(target = "tempoEntrega", source = "tempoEntrega"),
                @Mapping(target = "ativo", source = "ativo"),
                @Mapping(target = "dataCadastro", source = "dataCadastro"),

                // Endereço embutido
                @Mapping(target = "cep", source = "endereco.cep"),
                @Mapping(target = "logradouro", source = "endereco.logradouro"),
                @Mapping(target = "numero", source = "endereco.numero"),
                @Mapping(target = "complemento", source = "endereco.complemento"),
                @Mapping(target = "bairro", source = "endereco.bairro"),
                @Mapping(target = "cidade", source = "endereco.cidade"),
                @Mapping(target = "estado", source = "endereco.estado")
        })
        CervejariaResponseDTO toDTO(Cervejaria entity);


}
