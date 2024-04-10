package cat.cat_cafe;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
public class CatCafeRestTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/bookings")).andExpect(status().isOk());
        mockMvc.perform(get("/users")).andExpect(status().isOk());
        mockMvc.perform(get("/cats")).andExpect(status().isOk());
    }

    @Test
    public void responseIsJson() throws Exception {
        mockMvc.perform(get("/bookings"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

        mockMvc.perform(get("/users"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

        mockMvc.perform(get("/cats"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    public void apiStatusOk() throws Exception {
        mockMvc.perform(get("/api/bookings")).andExpect(status().isOk());
        mockMvc.perform(get("/api/appusers")).andExpect(status().isOk());
        mockMvc.perform(get("/api/cats")).andExpect(status().isOk());
    }


}
