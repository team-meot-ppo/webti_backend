package org.meotppo.webti.controller.result;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.meotppo.webti.config.ObjectMapperConfig;
import org.meotppo.webti.domain.dto.result.StatisticDTO;
import org.meotppo.webti.domain.entity.type.MbtiType;
import org.meotppo.webti.dto.result.TestResultRequest;
import org.meotppo.webti.service.result.ResultService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class ResultControllerTest {

    @InjectMocks
    private ResultController resultController;

    @Mock
    private ResultService resultService;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapperConfig().objectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(resultController).build();
    }

    @Test
    void testCreateTestResult() throws Exception {
        // given
        TestResultRequest request = new TestResultRequest(MbtiType.INFJ, true);

        // when & then
        mockMvc.perform(post("/api/results/test-result")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertToJson(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void testReadStatistics() throws Exception {
        // given
        List<StatisticDTO> statistics = List.of(
                new StatisticDTO("철저한 데이터 관리형 백엔드 개발자", 5L, 3L, LocalDateTime.now())
        );
        given(resultService.readStatistics()).willReturn(statistics);

        // when & then
        mockMvc.perform(get("/api/results/statistics"))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(statistics)));
    }

    private String convertToJson(Object object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }
}
