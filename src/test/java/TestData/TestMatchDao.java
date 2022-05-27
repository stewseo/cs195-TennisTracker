package TestData;
import TestModel.TestMatchModel;
import com.example.cs195tennis.model.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TestMatchDao {

    //mock matchdao instance
    @Mock
    TestMatchDao testMatchDao;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

     @Test
    public void TestMatchDao(){
        TestMatchDao testMatchDao = Mockito.mock(TestMatchDao.class);

        Mockito.when(testMatchDao.getMatchById(1)).thenReturn(new TestMatchModel("Australian Open", 1101));

        TestMatchModel m = testMatchDao.getMatchById(1);

        assert m.getName().equals("Australian Open 1101");

        assert (m.getMatchNum() == 1101);
    }

    public TestMatchModel getMatchById(int id) {
         return null;
    }
}

