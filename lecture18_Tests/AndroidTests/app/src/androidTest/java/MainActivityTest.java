import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tanya.androidtests.MainActivity;
import com.example.tanya.androidtests.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Admin on 7/30/2016.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    MainActivity mainAct;
  /*  @Rule
    int a = 10;

    @Rule
    int b = 11;

    @Before
    public void beforeTest(){
        a=a+b;

    }
*/

    @Rule
    public final ActivityTestRule<MainActivity> act = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUpActivity() throws Exception{
        mainAct = act.getActivity();
    }
    @After
    public void finishActivity() throws Exception {
        mainAct.finish();
    }

    @UiThreadTest@Test
    public void simpleFareTest() throws Exception{
        ((EditText)mainAct.findViewById(R.id.et_time)).setText("16");
        ((EditText)mainAct.findViewById(R.id.et_km)).setText("3");
        mainAct.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((Button)mainAct.findViewById(R.id.btn_calc)).callOnClick();

            }
        });

        assertEquals("Fare : 35.0" , ((TextView)mainAct.findViewById(R.id.tvFare)).getText());
    }

}
