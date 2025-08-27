package br.com.fumaca.repository;

import br.com.fumaca.model.pedido.Pedido;
import br.com.fumaca.model.pedido.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository <Pedido, Long>  {

    List<Pedido> findByClienteId(Long clienteId);
    List<Pedido> findByCervejariaId(Long cervejariaId);
    List<Pedido> findByStatus(StatusPedido status);
}
