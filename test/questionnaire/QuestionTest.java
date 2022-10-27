package questionnaire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * QuestionTest tests methods in the Question class on its Concrete Classes (Likert, YesNo, and
 *     ShortAnswer).
 */
public class QuestionTest {

  // Preallocate memory for creating Questions
  Likert likertRequired1;
  Likert likertRequired2;
  Likert likertRequired3;
  Likert likertOptional1;
  Likert likertOptional2;
  Likert likertOptional3;
  YesNo yesNoRequired1;
  YesNo yesNoRequired2;
  YesNo yesNoRequired3;
  YesNo yesNoOptional1;
  YesNo yesNoOptional2;
  YesNo yesNoOptional3;
  ShortAnswer shortAnswerRequired1;
  ShortAnswer shortAnswerRequired2;
  ShortAnswer shortAnswerRequired3;
  ShortAnswer shortAnswerOptional1;
  ShortAnswer shortAnswerOptional2;
  ShortAnswer shortAnswerOptional3;

  // Make longest String possible for ShortAnswer tests answerShortAnswer() and badAnswer()
  String LONGEST_STRING = "A poem by William Carlos Williams, "
      + "an American poet who was a general practitioner & a pediatrician."
      + "The title is: This is just to say"
      + "I have eaten"
      + " the plums"
      + " that were in"
      + " the icebox"

      + " and which"
      + " you were probably"
      + " saving"
      + " for breakfast"

      + " Forgive me"
      + " they were delicious"
      + " so sweet"
      + " and so cold";
  int LONGEST_STRING_LENGTH = 280;

  // Questions below will all fail (i.e., throw Exceptions) upon attempts at construction
  Likert likertFailNullPrompt;
  Likert likertFailEmptyPrompt;
  YesNo yesNoFailNullPrompt;
  YesNo yesNoFailEmptyPrompt;
  ShortAnswer shortAnswerFailNullPrompt;
  ShortAnswer shortAnswerFailEmptyPrompt;

  /**
   * setUp the Questions for testing.
   * @throws Exception - No exceptions thrown in setUp.
   */
  @Before
  public void setUp() throws Exception {
    likertRequired1 = new Likert("likertRequired1", true);
    likertRequired2 = new Likert("likertRequired2", true);
    likertRequired3 = new Likert("likertRequired3", true);
    likertOptional1 = new Likert("likertOptional1", false);
    likertOptional2 = new Likert("likertOptional2", false);
    likertOptional3 = new Likert("likertOptional3", false);

    yesNoRequired1 = new YesNo("yesNoRequired1", true);
    yesNoRequired2 = new YesNo("yesNoRequired2", true);
    yesNoRequired3 = new YesNo("yesNoRequired3", true);
    yesNoOptional1 = new YesNo("yesNoOptional1", false);
    yesNoOptional2 = new YesNo("yesNoOptional2", false);
    yesNoOptional3 = new YesNo("yesNoOptional3", false);

    shortAnswerRequired1 = new ShortAnswer("shortAnswerRequired1", true);
    shortAnswerRequired2 = new ShortAnswer("shortAnswerRequired2", true);
    shortAnswerRequired3 = new ShortAnswer("shortAnswerRequired3", true);
    shortAnswerOptional1 = new ShortAnswer("shortAnswerOptional1", false);
    shortAnswerOptional2 = new ShortAnswer("shortAnswerOptional2", false);
    shortAnswerOptional3 = new ShortAnswer("shortAnswerOptional3", false);
  }
  /* This test was created under the assumption that all Question objects required a prompt.
          However, Gradescope tests indicate that all Questions do NOT require a prompt and can
          be empty or null.

  @Test(expected = IllegalArgumentException.class)
  public void badConstruction() {
    // this test also functions as a proxy test of isValidString
    likertFailNullPrompt = new Likert(null, true);
    likertFailNullPrompt = new Likert(null, false);
    likertFailEmptyPrompt = new Likert ("", true);
    likertFailEmptyPrompt = new Likert("", false);
    yesNoFailNullPrompt = new YesNo(null, true);
    yesNoFailNullPrompt = new YesNo(null, false);
    yesNoFailEmptyPrompt = new YesNo("", true);
    yesNoFailEmptyPrompt = new YesNo("", false);
    shortAnswerFailNullPrompt = new ShortAnswer(null, true);
    shortAnswerFailNullPrompt = new ShortAnswer(null, false);
    shortAnswerFailEmptyPrompt = new ShortAnswer("", true);
    shortAnswerFailEmptyPrompt = new ShortAnswer("", false);
  }
  */

  /**
   * Tests the getPrompt() method for Question objects.
   */
  @Test
  public void getPrompt() {
    assertEquals("likertRequired1", likertRequired1.getPrompt());
    assertEquals("likertRequired2", likertRequired2.getPrompt());
    assertEquals("likertRequired3", likertRequired3.getPrompt());
    assertEquals("likertOptional1", likertOptional1.getPrompt());
    assertEquals("likertOptional2", likertOptional2.getPrompt());
    assertEquals("likertOptional3", likertOptional3.getPrompt());
    assertEquals("yesNoRequired1", yesNoRequired1.getPrompt());
    assertEquals("yesNoRequired2", yesNoRequired2.getPrompt());
    assertEquals("yesNoRequired3", yesNoRequired3.getPrompt());
    assertEquals("yesNoOptional1", yesNoOptional1.getPrompt());
    assertEquals("yesNoOptional2", yesNoOptional2.getPrompt());
    assertEquals("yesNoOptional3", yesNoOptional3.getPrompt());
    assertEquals("shortAnswerRequired1", shortAnswerRequired1.getPrompt());
    assertEquals("shortAnswerRequired2", shortAnswerRequired2.getPrompt());
    assertEquals("shortAnswerRequired3", shortAnswerRequired3.getPrompt());
    assertEquals("shortAnswerOptional1", shortAnswerOptional1.getPrompt());
    assertEquals("shortAnswerOptional2", shortAnswerOptional2.getPrompt());
    assertEquals("shortAnswerOptional3", shortAnswerOptional3.getPrompt());
  }

  /**
   * tests the isRequired() method on all Question concrete classes.
   */
  @Test
  public void isRequired() {
    assertTrue(likertRequired1.isRequired());
    assertTrue(likertRequired2.isRequired());
    assertTrue(likertRequired3.isRequired());
    assertFalse(likertOptional1.isRequired());
    assertFalse(likertOptional2.isRequired());
    assertFalse(likertOptional3.isRequired());
    assertTrue(yesNoRequired1.isRequired());
    assertTrue(yesNoRequired2.isRequired());
    assertTrue(yesNoRequired3.isRequired());
    assertFalse(yesNoOptional1.isRequired());
    assertFalse(yesNoOptional2.isRequired());
    assertFalse(yesNoOptional3.isRequired());
    assertTrue(shortAnswerRequired1.isRequired());
    assertTrue(shortAnswerRequired2.isRequired());
    assertTrue(shortAnswerRequired3.isRequired());
    assertFalse(shortAnswerOptional1.isRequired());
    assertFalse(shortAnswerOptional2.isRequired());
    assertFalse(shortAnswerOptional3.isRequired());
  }

  /**
   * Tests the answer() method on Likert objects.
   */
  @Test
  public void answerLikert() {
    // Possible LikertResponseOption answers (all case-insensitive) are below as separate Strings.
    String sd = "Strongly Disagree";
    likertRequired1.answer("strongly disagree");// case-insensitive sd
    assertTrue(likertRequired1.getAnswer().equalsIgnoreCase(sd));
    String d = "Disagree";
    likertRequired1.answer("diSAgReE");// case-insensitive d
    assertTrue(likertRequired1.getAnswer().equalsIgnoreCase(d));
    String n = "Neither Agree Nor Disagree";
    likertRequired1.answer("Neither Agree NOR disagree");// case-insensitive n
    assertTrue(likertRequired1.getAnswer().equalsIgnoreCase(n));
    String a = "Agree";
    likertRequired1.answer("agREE");// case-insensitive a
    assertTrue(likertRequired1.getAnswer().equalsIgnoreCase(a));
    String sa = "Strongly Agree";
    likertRequired1.answer("STRONGLY agREE");// case-insensitive a
    assertTrue(likertRequired1.getAnswer().equalsIgnoreCase(sa));
  }

  /**
   * Tests the answer() method on YesNo objects.
   * Legal answers are only "Yes" or "No", case-insensitive.
   */
  @Test
  public void answerYesNo() {
    // Possible YesNo answers (all case-insensitive) are below as separate Strings.
    String yes = "Yes";
    yesNoRequired1.answer("YES"); // ALL CAPS
    assertTrue(yesNoRequired1.getAnswer().equalsIgnoreCase(yes));
    yesNoRequired1.answer("yes");// all lowercase
    assertTrue(yesNoRequired1.getAnswer().equalsIgnoreCase(yes));
    yesNoRequired1.answer("YeS");// MiXeD CaSe
    assertTrue(yesNoRequired1.getAnswer().equalsIgnoreCase(yes));
    String no = "No";
    yesNoRequired1.answer("NO"); // ALL CAPS
    assertTrue(yesNoRequired1.getAnswer().equalsIgnoreCase(no));
    yesNoRequired1.answer("no");// all lowercase
    assertTrue(yesNoRequired1.getAnswer().equalsIgnoreCase(no));
    yesNoRequired1.answer("No");// MiXeD CaSe
    assertTrue(yesNoRequired1.getAnswer().equalsIgnoreCase(no));
  }

  /**
   * Tests the answer() method on ShortAnswer objects.
   * Legal answers are strings of a length between 1 and 280 characters.
   * No empty, null, or Strings of length > 280.
   */
  @Test
  public void answerShortAnswer() {
    // test one-char string (min possible length)
    shortAnswerRequired1.answer("a");
    assertEquals("a", shortAnswerRequired1.getAnswer());
    // test longest possible string (max possible length)
    assertEquals(LONGEST_STRING_LENGTH, LONGEST_STRING.length()); // ensure longString length
    shortAnswerRequired1.answer(LONGEST_STRING);
    assertEquals(LONGEST_STRING, shortAnswerRequired1.getAnswer());
  }



  /**
   * Tests invalid params as answers for Likert Questions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void badAnswerLikert() {
    /* For Likert, invalid params are null, empty, or strings that are not in the
            LikertResponseOptions class
     */
    likertRequired1.answer(null);
    likertRequired2.answer("");
    likertRequired3.answer("Slightly Disagree "); // added space at end of valid answer
    likertRequired1.answer(" Agree"); // added space at front of valid answer
    likertRequired1.answer("Agre"); // misspelling
  }

  /**
   * Tests invalid params as answers for YesNo Questions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void badAnswerYesNo() {
    // For YesNo, only "Yes" or "No", case-insensitive, are valid params.
    yesNoRequired1.answer(null);
    yesNoRequired1.answer("");
    yesNoRequired1.answer("Ye");
    yesNoRequired1.answer("es");
    yesNoRequired1.answer("Ye");
    yesNoRequired1.answer("Yes ");
    yesNoRequired1.answer(" Yes");
    yesNoRequired1.answer("Yeah");
    yesNoRequired1.answer("Sure");
    yesNoRequired1.answer(" No");
    yesNoRequired1.answer("Noes");
    yesNoRequired1.answer("Noe");
    yesNoRequired1.answer("Nope");
    yesNoRequired1.answer("No ");
    yesNoRequired1.answer("Yanni"); //  No "kinda-sorta" or "i mean..." in Arabic or Turkish
    yesNoRequired1.answer("Evet"); // No "Yes" in Turkish
    yesNoRequired1.answer("noOoOo"); // no suspicious no
    yesNoRequired1.answer("NO!"); // no really emphatic no
    yesNoRequired1.answer("Hell Yeah, Brother!"); // no Hulk Hogan "Yes"
    yesNoRequired1.answer("¯\\_(ツ)_/¯"); // no shrugging
  }

    /**
     * Tests invalid params as answers for ShortAnswer Questions.
     */
    @Test(expected = IllegalArgumentException.class)
    public void badAnswerShortAnswer() {
    // for ShortAnswer, no null, empty, or String of length > 280.
    shortAnswerRequired1.answer(null);
    shortAnswerRequired1.answer("");
    assertEquals(LONGEST_STRING_LENGTH, LONGEST_STRING.length()); // ensure longString length
    shortAnswerRequired1.answer(LONGEST_STRING + "."); // try to enter a String of length 281 char.
  }


  @Test
  public void getAnswer() {
  }

  /**
   * Tests the copy() method.
   * A copied Question should be of the same class, have the same answer, requirement,
   *     and prompt, but should not be equal to the copy because they are held at different
   *     memory addresses.
   */
  @Test
  public void copy() {
    assertFalse(likertRequired1.copy() == likertRequired1);
    assertTrue(likertRequired1.copy().getClass() == likertRequired1.getClass());
    assertTrue(likertRequired1.copy().getAnswer() == likertRequired1.getAnswer());
    assertTrue(likertRequired1.copy().isRequired() == likertRequired1.isRequired());
    assertTrue(likertRequired1.copy().getPrompt() == likertRequired1.getPrompt());



  }
}