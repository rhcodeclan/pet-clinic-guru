package org.rickhuizing.petclinicguru.controllers;

import org.hamcrest.Matchers;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void initializeOwnerForm() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/find"))
                .andExpect(model().attribute("owner", hasProperty("id", nullValue())));
    }

    @Test
    void findOwnerNoSearchTerm() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);
        mockMvc.perform(
                        get("/owners")
                                .requestAttr("owner", new Owner()))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/owners"))
                .andExpect(model().attribute("owners", owners));
        verify(ownerService, times(0)).findByLastName(anyString());
    }

    @Test
    void findOwnerByLastName() throws Exception {
        when(ownerService.findByLastNameContaining(anyString())).thenReturn(List.of(owner1));
        mockMvc.perform(
                        get("/owners")
                                .param("lastName", "aLastName"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/owners"))
                .andExpect(model().attribute("owners", Matchers.contains(owner1)));
        verify(ownerService, times(0)).findAll();
    }

    @Test
    void findOwnerByUnknownLastName() throws Exception {
        when(ownerService.findByLastNameContaining(anyString())).thenReturn(List.of());
        mockMvc.perform(
                        get("/owners")
                                .param("lastName", "unknownLastName"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/find"))
                .andExpect(model().attributeHasFieldErrors("owner", "lastName"));
        verify(ownerService, times(0)).findAll();
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
                .andExpect(view().name("error/notFound"))
                .andExpect(model().attribute("message", "Owner with ID 4 not found"));

        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void initCreateOwnerForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owner", Matchers.samePropertyValuesAs(new Owner())))
                .andExpect(view().name("/owners/createUpdateOwnerForm"));
        verifyNoInteractions(ownerService);
    }

    @Test
    void processCreateOwnerForm() throws Exception {
        // given
        Owner expectedOwner = Owner.builder()
                .address("a")
                .city("b")
                .telephone("1")
                .lastName("c")
                .firstName("b")
                .id(6L)
                .build();
        when(ownerService.save(any())).thenReturn(expectedOwner);
//        when(ownerService.findById(any())).thenReturn(expectedOwner);
        MockHttpServletRequestBuilder postRequest =
                post("/owners/new")
                        .param("address", "a")
                        .param("city", "b")
                        .param("telephone", "1")
                        .param("lastName", "c")
                        .param("firstName", "b");
        mockMvc.perform(postRequest)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/6"))
                .andExpect(model().attribute("owner", Matchers.samePropertyValuesAs(expectedOwner, "id", "new")));
        verify(ownerService).save(any());
    }

    @Test
    void processInvalidCreateOwnerForm() throws Exception {
        MockHttpServletRequestBuilder postRequest =
                post("/owners/new")
                        .param("telephone", "123456")
                        .param("lastName", "")
                        .param("firstName", "");
        mockMvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/createUpdateOwnerForm"))
                .andExpect(model().attributeHasErrors("owner"))
                .andExpect(model().attributeHasFieldErrors("owner", "address", "city"));
        verifyNoInteractions(ownerService);
    }

    @Test
    void initEditOwnerForm() throws Exception {

    }

    @Test
    void processEditOwnerForm() throws Exception {

    }

    @Test
    void processInvalidEditOwnerForm() throws Exception {

    }
}