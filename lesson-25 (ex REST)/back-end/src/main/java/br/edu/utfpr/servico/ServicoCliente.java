package br.edu.utfpr.servico;

//import br.edu.utfpr.dao.Pais;
import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.dto.PaisDTO;

import java.util.Optional;
import java.util.stream.Collectors;

import br.edu.utfpr.excecao.NomeClienteMenor5CaracteresException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
public class ServicoCliente {

    private List<ClienteDTO> clientes;
//    Pais pais = new Pais();
//    private PaisDTO PAIS;

    public ServicoCliente() {
        clientes = Stream.of(
                ClienteDTO.builder().id(1).nome("Jax Teller").idade(20).telefone("99828-1241").limiteCredito(229.68)
                        .pais(PaisDTO.builder().id(1).build())
                .build(),
                ClienteDTO.builder().id(2).nome("Rami Malek").idade(25).telefone("73112-9928").limiteCredito(2531.92)
                        .pais(PaisDTO.builder().id(2).build())
                .build(),
                ClienteDTO.builder().id(3).nome("Mike Ehrmantraut").idade(53).telefone("61382-0031").limiteCredito(5000.99)
                        .pais(PaisDTO.builder().id(3).build())
                .build(),
                ClienteDTO.builder().id(4).nome("Tig Trager").idade(48).telefone("43821-2190").limiteCredito(3819.12)
                        .pais(PaisDTO.builder().id(4).build())
                .build(),
                ClienteDTO.builder().id(5).nome("Otto Delaney").idade(55).telefone("94832-2190").limiteCredito(52.1)
                        .pais(PaisDTO.builder().id(2).build())
                .build()
        ).collect(Collectors.toList());
    }

    @GetMapping ("/servico/cliente")
    public ResponseEntity<List<ClienteDTO>> listar(){
        return ResponseEntity.ok(clientes);
    }

    @PostMapping ("/servico/cliente")
    public ResponseEntity<ClienteDTO> criar (@RequestBody ClienteDTO cliente){
        cliente.setId(clientes.size() + 1);
        clientes.add(cliente);

        return ResponseEntity.status(201).body(cliente);
    }

    // Excluindo um cliente com base no id informado
    @DeleteMapping("/servico/cliente/{id}")
    public ResponseEntity excluir(@PathVariable int id){

        // Se existe um cliente com o id informado, apaga. Se não, retorna 'não encontrado'
        if(clientes.removeIf(cliente -> cliente.getId() == id)){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }

    // Alterando um cliente com base no id informado
    @PutMapping  ("/servico/cliente/{id}")
    public ResponseEntity<ClienteDTO> alterar (@PathVariable int id, @RequestBody ClienteDTO cliente){

        // Encontrando o cliente com o id informado
        Optional<ClienteDTO> clienteExistente = clientes.stream().filter(cli -> cli.getId() == id).findAny();

        // Se o id existe, então altera
        clienteExistente.ifPresent(cli -> {
            // O nome do cliente pode disparar uma exceção, por isso o uso do try-catch
            try {
                cli.setNome(cliente.getNome());
            } catch (NomeClienteMenor5CaracteresException e) {
                e.printStackTrace();
            }
            cli.setIdade(cliente.getIdade());
            cli.setTelefone(cliente.getTelefone());
            cli.setLimiteCredito(cliente.getLimiteCredito());
            cli.setPais(cliente.getPais());
        });

        return ResponseEntity.of(clienteExistente);
    }

}
