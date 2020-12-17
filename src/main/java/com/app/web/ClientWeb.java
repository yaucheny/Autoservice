package com.app.web;

import com.app.model.entity.ClientEntity;
import com.app.service.api.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientWeb {
    private ClientService clientService;

    @GetMapping("/get-client-by-id/{id}")
    public ClientEntity findClientById(@PathVariable long id) {
        return clientService.findClientById(id);
    }

    @PostMapping("/save-client")
    public void addClient(@RequestBody ClientEntity userEntity) {
        clientService.addClient(userEntity);
    }

    @GetMapping("/find-all-clients")
    public List<ClientEntity> findAllClients() {
        return clientService.findAllClient();
    }

    @DeleteMapping("/delete-client")
    public void deleteClient(@RequestBody ClientEntity userEntity) {
        clientService.deleteClient(userEntity);
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}