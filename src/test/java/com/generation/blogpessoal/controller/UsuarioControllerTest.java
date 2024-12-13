package com.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;
import com.generation.blogpessoal.service.UsuarioService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    void start(){

        usuarioRepository.deleteAll();

        usuarioService.cadastrarUsuario(new Usuario(null,"root","root@root.com","12345678","-"));
    }

    @Test
    @DisplayName("Cadastrar um usuario")
    public void deveCriarUmUsuario(){

        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>
                (new Usuario(null,"Paulo Antunes", "paulo@gmail.com","12345667","-"));

        ResponseEntity<Usuario> corpoResposta = testRestTemplate
                .exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);

        assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());

    }

    @Test
    @DisplayName("Não deve permitir duplicação de usuario")
    public void naoDevePermitirDuplicarUsuario(){

        usuarioService.cadastrarUsuario(new Usuario(null,"teste1","teste@test.com","12345678","-"));

        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(new Usuario(null,"teste1","teste@test.com","12345678","-"));

        ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar",HttpMethod.POST,corpoRequisicao,Usuario.class);

        assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());

    }

    @Test
    @DisplayName("Atualiza um usuario")
    public void deveAtualizarUmUsuario(){

        Optional<Usuario> usuarioCadastrado = usuarioService.cadastrarUsuario(new Usuario(null,"teste1","teste21@test.com","12345678","-"));

        if (usuarioCadastrado.isEmpty()) {
            throw new RuntimeException("Erro ao cadastrar o usuário no teste.");
        }

        Usuario usuarioUpdate = new Usuario(
                usuarioCadastrado.get().getId(),
                "teste2atualizado",
                "teste21@test.com",
                "12345678",
                "-"
        );


        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(usuarioUpdate);

        ResponseEntity<Usuario> corpoResposta = testRestTemplate
                .withBasicAuth("root@root.com","12345678")
                .exchange("/usuarios/atualizar",HttpMethod.PUT,corpoRequisicao,Usuario.class);

        assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());

    }

    @Test
    @DisplayName("Listar todos os Usuarios")
    public void deveMostrarTodosUsuarios(){

        usuarioService.cadastrarUsuario(new Usuario(null,"teste1","teste@test.com","12345678","-"));
        usuarioService.cadastrarUsuario(new Usuario(null,"teste2","teste2@test.com","12345678","-"));

        ResponseEntity<String> corpoResposta = testRestTemplate
                .withBasicAuth("root@root.com","12345678")
                .exchange("/usuarios/listar",HttpMethod.GET,null,String.class);

        assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
    }



}
