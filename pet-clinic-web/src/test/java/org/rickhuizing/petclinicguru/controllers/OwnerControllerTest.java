package org.rickhuizing.petclinicguru.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rickhuizing.petclinicguru.configuration.ErrorControllerAdvice;
import org.rickhuizing.petclinicguru.model.Owner;
import org.rickhuizing.petclinicguru.services.OwnerService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> owners;
    Owner owner1;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();

        owner1 = Owner.builder().id(1L).build();
        owners.add(owner1);
        owners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController)
                .setControllerAdvice(new ErrorControllerAdvice())
                .build();
    }

    @Test
    void listOwners() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);
        mockMvc.perform(get("/owners/"))
                .andExpect(status().is(200))
                .andExpect(view().name("owners/owners"))
                .andExpect(model().attribute("owners", owners));
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/find"));
    }


    @Test
    void getOwnerHappy() throws Exception {
        when(ownerService.findById(1L)).thenReturn(owner1);

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owner", owner1))
                .andExpect(view().name("owners/viewOwner"));

        verify(ownerService, times(1)).findById(1L);
    }

    @Test
    void getOwnerSad() throws Exception {
        // when unknown id
        when(ownerService.findById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/owners/4"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("/notFound"))
                .andExpect(model().attribute("message", "Owner with ID 4 not found"));

        verify(ownerService, times(1)).findById(anyLong());
    }
}