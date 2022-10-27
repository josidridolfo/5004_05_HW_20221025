package questionnaire;

/**
 * Likert class members are Questions that have a String that is one of the txt values in a
 * LikertResponseOption as an answer.
 */
public class Likert extends AbstractQuestion {
  public static final int MINIMUM_LIKERT_RESPONSE_VAL = -2;

  /**
   * Constructor for a Likert Question.
   * @param question - String.
   * @param isRequired - Boolean, is the question required? True iff yes, false otherwise.
   */
  public Likert(String question, boolean isRequired) {
    super(question, isRequired);
  }

  /**
   * Enables the user to pass an answer to the Likert object.
   * @param answer - String, but value must be equal to one of the txt values in a
   *               LikertResponseOption enum. Cannot be null or empty.
   * @throws IllegalArgumentException iff answer is null, empty, or not among those possible
   *              String values found in LikertResponseOption.txt.
   */
  @Override
  public void answer(String answer) throws IllegalArgumentException {
    if (!isValidString(answer)) { // string is null or empty
      throw new IllegalArgumentException("Answer cannot be null or empty.");
    }
    boolean flag = false; // flag to find whether answer is an LikertResponseOption enum txt value.
    // iterate through all possible LikertResponseOption values and compare with answer param.
    for (LikertResponseOption likertAnswers : LikertResponseOption.values()) {
      // if the answer param is one of the LikertResponseOption.txt values, change the flag to true.
      if (answer.equalsIgnoreCase(likertAnswers.getText())) {
        flag = true;
        break; // if flag changes, then break out of for loop;
      }
    }
    if (!flag) { // The for loop did not find a match, and so answer is not a LikertResponseOption.
      throw new IllegalArgumentException("Answer must be a valid LikertResponseOption!");
    }
    this.answer = answer;
  }

  /**
   *  Creates a copy of a Likert Question.
   * @return A copy of the Likert Question with all instance variables.
   */
  @Override
  public Likert copy() {
    Likert copyQuestion = new Likert(this.getPrompt(), this.isRequired());
    // only copy the answer if it is not empty or null.
    if (isValidString(this.getAnswer())) {
      copyQuestion.answer(this.getAnswer());
    }
    return copyQuestion;
  }
}
