import com.javarush.khmelov.entity.Question;
import com.javarush.khmelov.dao.AnswerDAO;
import com.javarush.khmelov.dao.QuestDAO;
import com.javarush.khmelov.dao.QuestionDAO;
import com.javarush.khmelov.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


public class QuestionServiceTest {

    private QuestionDAO questionRepository;
    private AnswerDAO answerRepository;
    private QuestDAO questRepository;
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        questionRepository = new QuestionDAO();
        answerRepository = new AnswerDAO();
        questRepository = new QuestDAO();
        questionService = new QuestionService(questRepository, questionRepository, answerRepository);
    }

    @Test
    @DisplayName("testGetNextQuestionWithNullAnswerId")
    void testGetNextQuestionWithNullAnswerId() {
        Optional<Question> questionOptional = questionService.getNextQuestion(null);
        assertTrue(questionOptional.isPresent());
        assertEquals(1L, questionOptional.get().getId()); // Assuming starting question has ID 1
    }

    @Test
    @DisplayName("testGetNextQuestionWithValidAnswerId")
    void testGetNextQuestionWithValidAnswerId() {
        Optional<Question> questionOptional = questionService.getNextQuestion(1L); // Assuming answer with ID 1 leads to question with ID 2
        assertTrue(questionOptional.isPresent());
        assertEquals(2L, questionOptional.get().getId());
    }

    @Test
    @DisplayName("testGetNextQuestionWithInvalidAnswerId")
    void testGetNextQuestionWithInvalidAnswerId() {
        Optional<Question> questionOptional = questionService.getNextQuestion(999L); // Non-existent answer ID
        assertFalse(questionOptional.isPresent());
    }

    @Test
    @DisplayName("testGetStartingQuestionForQuestWithValidQuestId")
    void testGetStartingQuestionForQuestWithValidQuestId() {
        Optional<Question> questionOptional = questionService.getStartingQuestionForQuest(1L); // Assuming quest with ID 1 has starting question with ID 1
        assertTrue(questionOptional.isPresent());
        assertEquals(1L, questionOptional.get().getId());
    }

}
