package br.ufscar.dc.dsw.AA1Veiculos.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.AA1Veiculos.dao.IClienteDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Usuario;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IClienteService;

@Service
@Transactional(readOnly = false)
public class ClienteService implements IClienteService {
    
    @Autowired
    private IClienteDAO dao;

    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return dao.findById(id.longValue()).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarTodos() {
        return dao.findAll();
    }

    public void salvar(Cliente cliente) {
        dao.save(cliente);
    }

    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Cliente buscarPorUsuario(Usuario usuario) {
        return dao.findByUsuario(usuario);
    }

    @Override
    public Cliente buscarPorEmailUsuario(String email) {
        return dao.findByUsuarioEmail(email);
    }
    
}
