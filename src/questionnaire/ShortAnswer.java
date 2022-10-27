package questionnaire;


/**
 * * ShortAnswer class for questions that have short answer responses.
 */
public class ShortAnswer extends AbstractQuestion {
  public static final int MAX_SHORT_ANSWER_LENGTH = 280;

  /**
   * ShortAnswer Constructor.
   * @param prompt - String (the question to be asked)
   * @param required - boolean (is the question required?)
   */
  public ShortAnswer(String prompt, boolean required) {
    super(prompt, required);
  }

  @Override
  public void answer(String answer) throws IllegalArgumentException {
    if (!isValidString(answer)) { // for null or empty strings
      throw new IllegalArgumentException("Response cannot be empty! "
          + "Please provide a valid response (up to "
          + MAX_SHORT_ANSWER_LENGTH + " characters.");
    } // end if string empty or null
    if (answer.length() > MAX_SHORT_ANSWER_LENGTH) { // for strings greater than the max length
      throw new IllegalArgumentException("Please provide a shorter response!"
          + " Max response length is " + MAX_SHORT_ANSWER_LENGTH + " characters.");
    } // end if string too big
    this.answer = answer;
  }

  /**
   *  Creates a copy of a ShortAnswer Question.
   * @return A copy of the ShortAnswer Question with all instance variables.
   */
  @Override
  public ShortAnswer copy() {
    ShortAnswer copiedQuestion = new ShortAnswer(this.getPrompt(), this.isRequired());
    copiedQuestion.answer(this.getAnswer());
    return copiedQuestion;
  }
}
