package br.com.fumaca.Mapper;

import br.com.fumaca.dto.ItemPedidoRequestDTO;
import br.com.fumaca.dto.ItemPedidoResponseDTO;
import br.com.fumaca.dto.PedidoRequestDTO;
import br.com.fumaca.dto.PedidoResponseDTO;
import br.com.fumaca.model.Endereco;
import br.com.fumaca.model.pedido.ItemPedido;
import br.com.fumaca.model.pedido.Pedido;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    // 🔄 Pedido → ResponseDTO
    @Mapping(target = "cliente", source = "cliente")
    @Mapping(target = "cervejaria", source = "cervejaria")
    @Mapping(target = "itens", source = "itens")
    PedidoResponseDTO toDTO(Pedido pedido);

    // 🔄 ItemPedido → ResponseDTO
    @Mapping(target = "nomeCerveja", source = "cerveja.nome")
    ItemPedidoResponseDTO toItemDTO(ItemPedido item);

    List<ItemPedidoResponseDTO> toItemDTOList(List<ItemPedido> itens);

    // 🔄 RequestDTO → Endereco (manual)
    default Endereco mapEndereco(PedidoRequestDTO dto) {
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

    // 🔄 RequestDTO → ItemPedido (manual)
    default ItemPedido toItemEntity(ItemPedidoRequestDTO dto) {
        if (dto == null) return null;
        ItemPedido item = new ItemPedido();
        item.setQuantidade(dto.getQuantidade());
        // cerveja será setada no service após busca
        return item;
    }

    default List<ItemPedido> toItemEntityList(List<ItemPedidoRequestDTO> dtos) {
        return dtos.stream()
                .map(this::toItemEntity)
                .toList();
    }
}
