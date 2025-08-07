package br.com.fumaca.service;


import br.com.fumaca.dto.UsuarioRequestDTO;
import br.com.fumaca.dto.UsuarioResponseDTO;
import br.com.fumaca.enterprise.CustomValidationException;
import br.com.fumaca.model.usuario.Usuario;
import br.com.fumaca.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO criarUsuario(@Valid UsuarioRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new CustomValidationException("Já existe um usuário com este e-mail.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha()); // lembre-se de criptografar depois!
        usuario.setRoles(dto.getRoles());

        Usuario salvo = usuarioRepository.save(usuario);
        return toResponseDTO(salvo);
    }

    public List<UsuarioResponseDTO> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new CustomValidationException("Usuário não encontrado."));
        return toResponseDTO(usuario);
    }

    public UsuarioResponseDTO atualizarUsuario(UUID id, @Valid UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new CustomValidationException("Usuário não encontrado."));

        // Atualiza campos
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setRoles(dto.getRoles());

        Usuario atualizado = usuarioRepository.save(usuario);
        return toResponseDTO(atualizado);
    }

    public void deletarUsuario(UUID id) {
        if (!usuarioRepository.existsById(id)) {
            throw new CustomValidationException("Usuário não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }


    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRoles()
        );
    }
}
