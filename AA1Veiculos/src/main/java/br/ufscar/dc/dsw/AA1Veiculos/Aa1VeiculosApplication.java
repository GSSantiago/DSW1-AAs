package br.ufscar.dc.dsw.AA1Veiculos;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.AA1Veiculos.dao.IVeiculoDAO;
import br.ufscar.dc.dsw.AA1Veiculos.dao.ILojaDAO;
import br.ufscar.dc.dsw.AA1Veiculos.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.AA1Veiculos.dao.IClienteDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Usuario;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Veiculo;

@SpringBootApplication
public class Aa1VeiculosApplication {

    public static void main(String[] args) {
        SpringApplication.run(Aa1VeiculosApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner demo(
            IVeiculoDAO veiculoDAO,
            IUsuarioDAO usuarioDAO,
            ILojaDAO lojaDAO,
            IClienteDAO clienteDAO,
            BCryptPasswordEncoder encoder) {
        
        return args -> {
            if (usuarioDAO.count() == 0) {
                Usuario admin = new Usuario();
                admin.setName("Administrador");
                admin.setEmail("admin@admin.com");
                admin.setSenha(encoder.encode("admin"));
                admin.setRole("ROLE_ADMIN");
                usuarioDAO.save(admin);
            }
            
            if (lojaDAO.count() == 0) {
                Usuario user1 = new Usuario();
                user1.setEmail("contato@autoprime.com");
                user1.setSenha(encoder.encode("senha123"));
                user1.setName("AutoPrime Veículos");
                user1.setRole("ROLE_LOJA");
                usuarioDAO.save(user1);

                Loja loja1 = new Loja();
                loja1.setUsuario(user1);
                loja1.setCnpj("12.345.678/0001-90");
                loja1.setDescricao("Concessionária especializada em seminovos com garantia.");
                lojaDAO.save(loja1);
                
                Usuario user2 = new Usuario();
                user2.setName("CarrosVIP");
                user2.setEmail("vendas@carrosvip.com");
                user2.setSenha(encoder.encode("senha456"));
                user2.setRole("ROLE_LOJA");
                usuarioDAO.save(user2);

                Loja loja2 = new Loja();
                loja2.setUsuario(user2);
                loja2.setCnpj("98.765.432/0001-10");
                loja2.setDescricao("Líder em vendas de carros de luxo na região.");
                lojaDAO.save(loja2);
            }
            
            if (veiculoDAO.count() == 0) {
                Loja loja1 = lojaDAO.findAll().get(0);
                Loja loja2 = lojaDAO.findAll().get(1);
                
                Veiculo v1 = new Veiculo();
                v1.setLoja(loja1);
                v1.setPlaca("ABC1D23");
                v1.setModelo("Honda Civic EX 2.0");
                v1.setChassi("9BWZZZ377VT004251");
                v1.setAno(2021);
                v1.setQuilometragem(35000);
                v1.setValor(BigDecimal.valueOf(102500.00));
                v1.setDescricao("Sedã confortável, excelente estado, IPVA pago.");
                veiculoDAO.save(v1);

                Veiculo v2 = new Veiculo();
                v2.setLoja(loja1);
                v2.setPlaca("DEF4G56");
                v2.setModelo("Toyota Corolla Altis");
                v2.setChassi("8AGZZZ123BR456789");
                v2.setAno(2020);
                v2.setQuilometragem(47000);
                v2.setValor(BigDecimal.valueOf(110900.00));
                v2.setDescricao("Único dono, todas as revisões feitas em concessionária.");
                veiculoDAO.save(v2);

                Veiculo v3 = new Veiculo();
                v3.setLoja(loja2);
                v3.setPlaca("XYZ9H88");
                v3.setModelo("Toyota Corolla Altis");
                v3.setChassi("WBAZZZ379AC987654");
                v3.setAno(2019);
                v3.setQuilometragem(60000);
                v3.setValor(BigDecimal.valueOf(158900.00));
                v3.setDescricao("SUV de luxo, teto solar, bancos de couro.");
                veiculoDAO.save(v3);
            }
            
            if (clienteDAO.count() == 0) {
                Usuario cUser1 = new Usuario();
                cUser1.setName("Maria Santos");
                cUser1.setEmail("w1374066@gmail.com");
                cUser1.setSenha(encoder.encode("maria123"));
                cUser1.setRole("ROLE_CLIENTE");
                usuarioDAO.save(cUser1);

                Cliente c1 = new Cliente();
                c1.setUsuario(cUser1);
                c1.setCpf("12345678901");
                c1.setTelefone("11988776655");
                c1.setSexo("F");
                c1.setDataNascimento(LocalDate.of(1990, 5, 15));
                clienteDAO.save(c1);

                Usuario cUser2 = new Usuario();
                cUser2.setName("João Pereira");
                cUser2.setEmail("joao.pereira@example.com");
                cUser2.setSenha(encoder.encode("joaopass"));
                cUser2.setRole("ROLE_CLIENTE");
                usuarioDAO.save(cUser2);

                Cliente c2 = new Cliente();
                c2.setUsuario(cUser2);
                c2.setCpf("10987654321");
                c2.setTelefone("21999887766");
                c2.setSexo("M");
                c2.setDataNascimento(LocalDate.of(1985, 8, 30));
                clienteDAO.save(c2);
            }
        };
    }
}
