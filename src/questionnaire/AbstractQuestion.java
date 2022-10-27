package questionnaire;

/**
 * * AbstractQuestion class objects are Questions that can be utilized in a Questionnaire.
 *    Each AbstractQuestion has a prompt (String), a boolean notifying whether the question is
 *    required, and an answer (String).
 */
public abstract class AbstractQuestion implements Question {
  protected String prompt;
  protected boolean required;
  protected String answer;

  /**
   * Constructor for AbstractQuestion.
   * @param prompt - String, the question to be asked.
   * @param required - boolean. Must the question be answered by the respondent? True iff yes.
   */
  public AbstractQuestion(String prompt, boolean required) {
    this.prompt = prompt;
    this.required = required;
    this.answer = ""; // answer is an empty String at construction
  }

  /**
   * Provides the user with the Question's prompt (String).
   * @return - the Question's prompt (String).
   */
  @Override
  public String getPrompt() {
    return this.prompt;
  }

  /**
   * Returns to the user the requirement of the Question. True iff required; false if optional.
   * @return - Boolean. See above line for details.
   */
  @Override
  public boolean isRequired() {
    return this.required;
  }

  /**
   * Enables the user to provide an answer. Different for all concrete classes that extend the
   *     AbstractQuestion class.
   * @param answer - String, cannot be null or empty. Additional restrictions apply to each
   *     concrete class.
   */
  @Override // different for all concrete classes
  public void answer(String answer) {
    return;
  }

  /**
   * Returns the Question's answer (String).
   */
  @Override
  public String getAnswer() {
    return this.answer;
  }

  /**
   * Returns a copy of the Question, including all instance variables.
   * Because copy() returns different concrete classes, each concrete class has its own
   * implementation, but the code is basically identical. Perhaps a violation of DRY?
   */
  @Override
  public Question copy() {
    return null;
  }

  /**
   * Helper method to ensure that strings are valid (i.e., are not null and are not empty).
   * @param string - String for checking validity.
   * @return - true iff param is neither null nor empty.
   */
  public boolean isValidString(String string) {
    if (string == null) {
      return false;
    } // null string returns false
    if (string.isEmpty()) {
      return false;
    } // empty string returns false
    return true; // all other cases pass
  }
}
