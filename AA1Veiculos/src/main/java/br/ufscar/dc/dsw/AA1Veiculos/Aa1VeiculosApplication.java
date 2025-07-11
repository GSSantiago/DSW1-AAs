package br.ufscar.dc.dsw.AA1Veiculos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.AA1Veiculos.dao.IClienteDAO;
import br.ufscar.dc.dsw.AA1Veiculos.dao.ILojaDAO;
import br.ufscar.dc.dsw.AA1Veiculos.dao.IVeiculoDAO;
import br.ufscar.dc.dsw.AA1Veiculos.dao.IUsuarioDAO;
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
            ILojaDAO lojaDAO,
            IVeiculoDAO veiculoDAO,
            IClienteDAO clienteDAO,
            IUsuarioDAO usuarioDAO,
            BCryptPasswordEncoder encoder) {
        return args -> {
            String adminEmail = "admin@admin.com";
            if (usuarioDAO.findByEmail(adminEmail) == null) {
                Usuario admin = new Usuario();
                admin.setNome("Administrador");
                admin.setEmail(adminEmail);
                admin.setSenha(encoder.encode("admin"));
                admin.setPapel("ADMIN");
                usuarioDAO.save(admin);
            }

            if (lojaDAO.count() <= 1) {
                Loja loja1 = new Loja();
                loja1.setNome("AutoPrime Veículos");
                loja1.setEmail("contato@autoprime.com");
                loja1.setSenha(encoder.encode("senha123"));
                loja1.setCnpj("12.345.678/0001-90");
                loja1.setDescricao("Concessionária especializada em seminovos com garantia.");
                loja1.setPapel("LOJA");
                lojaDAO.save(loja1);

                Loja loja2 = new Loja();
                loja2.setNome("CarrosVIP");
                loja2.setEmail("vendas@carrosvip.com");
                loja2.setSenha(encoder.encode("senha456"));
                loja2.setCnpj("98.765.432/0001-10");
                loja2.setDescricao("Líder em vendas de carros de luxo na região.");
                loja2.setPapel("LOJA");
                lojaDAO.save(loja2);
            }

            if (veiculoDAO.count() == 0) {
                List<Loja> lojas = lojaDAO.findAll();
                lojas = lojas.stream()
                        .filter(l -> !l.getEmail().equals(adminEmail))
                        .toList();
                Loja loja1 = lojas.get(0);
                Loja loja2 = lojas.get(1);

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
                Cliente c1 = new Cliente();
                c1.setNome("Maria Santos");
                c1.setEmail("w1374066@gmail.com");
                c1.setSenha(encoder.encode("maria123"));
                c1.setCpf("193.256.660-06");
                c1.setTelefone("11988776655");
                c1.setSexo("F");
                c1.setNascimento(LocalDate.of(1990, 5, 15));
                c1.setPapel("CLIENTE");
                clienteDAO.save(c1);
                
                Cliente c2 = new Cliente();
                c2.setNome("João Pereira");
                c2.setEmail("gui.sw03@gmail.com");
                c2.setSenha(encoder.encode("gui123"));
                c2.setCpf("944.511.010-26");
                c2.setTelefone("21999887766");
                c2.setSexo("M");
                c2.setNascimento(LocalDate.of(1985, 8, 30));
                c2.setPapel("CLIENTE");
                clienteDAO.save(c2);
            }
        };
    }
}
