package com.nz.simpleshop.service;

import com.nz.simpleshop.model.Client;
import com.nz.simpleshop.repository.ClientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements UserDetailsService {

    private final static Logger logger = LogManager.getLogger(ClientService.class);
    private final static String DEFAULT_ROLE = "ROLE_USER";

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return clientRepository.getClientByLogin(login);
    }

    public Client createNewUser(String login, String password) {
        Client client = new Client();
        client.setLogin(login);
        client.setPasshash(passwordEncoder.encode(password));
        client.setRole(DEFAULT_ROLE);
        clientRepository.save(client);
        logger.info("Creating new user with login {} and password {}", login, password);
        return client;
    }
}
