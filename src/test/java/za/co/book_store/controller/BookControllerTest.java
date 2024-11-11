package za.co.book_store.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import za.co.book_store.service.BookService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static za.co.book_store.data.TestData.getBookDto;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    @Test
    void saveBook_success() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(getBookDto());
        when(bookService.saveBook(any())).thenReturn(getBookDto());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/store-book")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(getBookDto().getTitle()))
                .andExpect(jsonPath("$.author").value(getBookDto().getAuthor()))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertNotNull(response);
    }

    @Test
    void saveBook_badRequest() throws Exception {
        when(bookService.saveBook(any())).thenReturn(getBookDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/store-book")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }

    @Test
    void updateBook_success() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(getBookDto());
        when(bookService.updateBook(any(), any())).thenReturn(getBookDto());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/books/1")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(getBookDto().getTitle()))
                .andExpect(jsonPath("$.author").value(getBookDto().getAuthor()))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertNotNull(response);
    }

    @Test
    void updateBook_badRequest() throws Exception {
        when(bookService.updateBook(any(), any())).thenReturn(getBookDto());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }
}
