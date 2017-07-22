package com.nz.simpleshop.service;

import com.nz.simpleshop.model.Client;
import com.nz.simpleshop.repository.ClientRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateNewUser() {
        String login = "login";
        String password = "password";

        Client client = clientService.createNewUser(login, password);
        String hash = passwordEncoder.encode(password);

        Assert.assertNotNull(client);
        Assert.assertEquals(client.getLogin(), login);
        Assert.assertEquals(client.getPassword(), hash);
        Assert.assertEquals(client.getPasshash(), hash);

        client.getAuthorities().forEach((p) -> Assert.assertEquals(p.getAuthority(), "ROLE_USER"));
    }
}