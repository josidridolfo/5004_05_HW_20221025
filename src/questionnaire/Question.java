package questionnaire;

/**
 * * Question Interface - contract for all Question types.
 */
public interface Question {

  /**
   * Enables the user to get the Question's prompt.
    * @return String - this.prompt.
   */
  String getPrompt();

  /**
   * Enables the user to get whether the question is required.
   * @return boolean - this.required.
   */
  boolean isRequired();

  /**
   * Enables the user to answer the Question.
   * @param string Answer of the question. All concrete classes have different answer constraints.
   */
  void answer(String string);

  /**
   * Returns the answer of a Question.
   * @return - String, this.answer.
   */
  String getAnswer();

  /**
   * Creates a copy of the Question (i.e., makes a new Question, and populates the instance
   * variables of that copy with the values in the Question that is being copied).*
   * @return - Question.
   */
  Question copy();

}
