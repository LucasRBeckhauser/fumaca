package br.com.fumaca.mapper;

import br.com.fumaca.dto.CervejariaRequestDTO;
import br.com.fumaca.dto.CervejariaResponseDTO;
import br.com.fumaca.model.Cervejaria;
import br.com.fumaca.model.Endereco;
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
                @Mapping(target = "endereco", expression = "java(mapEndereco(dto))")
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
                @Mapping(target = "cep", source = "endereco.cep"),
                @Mapping(target = "logradouro", source = "endereco.logradouro"),
                @Mapping(target = "numero", source = "endereco.numero"),
                @Mapping(target = "complemento", source = "endereco.complemento"),
                @Mapping(target = "bairro", source = "endereco.bairro"),
                @Mapping(target = "cidade", source = "endereco.cidade"),
                @Mapping(target = "estado", source = "endereco.estado")
        })
        CervejariaResponseDTO toDTO(Cervejaria entity);

        default Cervejaria toMinimalDTO(Cervejaria entity) {
                if (entity == null) return null;
                Cervejaria minimal = new Cervejaria();
                minimal.setId(entity.getId());
                return minimal;
        }

        // ✅ Método manual para construir o endereço
        default Endereco mapEndereco(CervejariaRequestDTO dto) {
                if (dto == null) return null;

                Endereco endereco = new Endereco();
                endereco.setCep(dto.getCep());
                endereco.setLogradouro(dto.getLogradouro());
                endereco.setNumero(dto.getNumero());
                endereco.setComplemento(dto.getComplemento());
                endereco.setBairro(dto.getBairro());
                endereco.setCidade(dto.getCidade());
                endereco.setEstado(dto.getEstado());

                return endereco;
        }
}
