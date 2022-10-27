package questionnaire;

/**
 * YesNo class members are Questions that have answers restricted to Yes or No.
 */
public class YesNo extends AbstractQuestion {

  /**
   * Constructor for a YesNo question.
   * @param prompt - String, cannot be null.
   * @param required - boolean. Is the question required? True iff yes, false otherwise.
   */
  public YesNo(String prompt, boolean required) {
    super(prompt, required);
    this.answer = null; // answers are provided by the questionnaire respondent after construction
  }

  /**
   * Enables the user to provie an answer to the question. Answers must be either 'yes' or 'no'.
   *   Answers are case-insensitive.
   * *
   * @param answer - String, either 'yes' or 'no' or some variation.
   *               E.g., "Yes", "YeS", "no", "NO", "nO", "No", etc., are all permissible.
   * @throws IllegalArgumentException iff answer is empty, null, or not 'yes' or 'no'.
   */
  @Override
  public void answer(String answer) throws IllegalArgumentException {
    String yes = "yes";
    String no = "no";
    if (!isValidString(answer)) {
      throw new IllegalArgumentException("Please enter a valid response - either Yes or No.");
    }
    if (!answer.equalsIgnoreCase(yes) && !answer.equalsIgnoreCase(no)) {
      throw new IllegalArgumentException("Please provide a valid response. Must be yes or no.");
    }
    this.answer = answer;
  }

  /**
   * Creates a copy of a YesNo Question.
   * @return A copy of the YesNo Question with all instance variables.
   */
  @Override
  public YesNo copy() {
    YesNo copiedQuestion = new YesNo(this.getPrompt(), this.isRequired());
    copiedQuestion.answer(this.getAnswer());
    return copiedQuestion;
  }
}

