package questionnaire;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * * QuestionnaireImpl for implementing a Questionnaire.
 * A QuestionnaireImpl contains Questions in a LinkedHashMap.
 */
public class QuestionnaireImpl implements Questionnaire {
  private LinkedHashMap<String, Question> questionnaire;

  /**
   * Constructor for a QuestionnaireImpl. Creates an empty LinkedHashMap.
   */
  public QuestionnaireImpl() {
    this.questionnaire = new LinkedHashMap<>();
  }

  /**
   * Add a question to the questionnaire.
   *
   * @param identifier a name for the question <b>unique</b> within this questionnaire. Not null
   *                   or empty.
   * @param q          the {@link Question} to be added to the questionnaire
   */
  @Override
  public void addQuestion(String identifier, Question q) {
    // null or empty identifiers are not added.
    if (isValidString(identifier)) this.questionnaire.putIfAbsent(identifier, q);
  }

  /**
   * Remove the question with the given identifier from the questionnaire.
   *
   * @param identifier the identifier of the question to be removed.
   * @throws NoSuchElementException if there is no question with the given identifier.
   */
  @Override
  public void removeQuestion(String identifier) throws NoSuchElementException {
    // first, get the Question (value) in the Questionnaire (LinkedHashMap) that corresponds to
    // the identifier (key).
    // if the identifier is null or empty, or if the Question is null, throw an exception
    if (!questionnaire.containsKey(identifier)) {
      throw new NoSuchElementException("No item found!");
    }
    // if we're here, then we've identifier is a key and found is the value. Remove it.
    this.questionnaire.remove(identifier);
  }

  /**
   * Get the question with the given number, based on the order in which it was added to the
   * questionnaire, or the sorted order if the {@code sort()} method is called. The first question
   * is 1, second 2, etc.
   *
   * @param num the number of the question, counting from 1
   * @return the question
   * @throws IndexOutOfBoundsException if there is no such question num
   */
  @Override
  public Question getQuestion(int num) {
    if (num < 1) { // error catch #1 - min. value of num is 1
      throw new IndexOutOfBoundsException("Number passed through must be at least 1.");
    } // end error catch #1 - num has min value of 1;
    num -= 1; // reduce number by 1 to get index;
    // convert hashmap to list of questions.
    List<Question> questionList = new ArrayList<Question>(this.questionnaire.values());
    int questionListLength = questionList.size(); // get list size (i.e., questionnaire size)
    if (num > questionListLength) { // error catch # 2
      // input num cannot be greater than questionnaire size
      throw new IndexOutOfBoundsException("Number passed through cannot be greater than "
          + "questionnaire size.");
    } // end error catch #2
    return questionList.get(num);
  }

  /**
   * Get the question with the given identifier (question having been previously added to the
   * questionnaire).
   *
   * @param identifier the identifier of the question
   * @return the question
   * @throws NoSuchElementException if there is no question with the identifier
   */
  @Override
  public Question getQuestion(String identifier) {
    Question found = this.questionnaire.get(identifier);
    if (!isValidString(identifier) || found == null) {
      throw new NoSuchElementException("No such element found!");
    } // identifiers cannot be null
    return found;
  }

  /**
   * Return a list of all required questions in the questionnaire.
   *     NB: First converts to LinkedHashMap to ArrayList (O(n)), then
   *     traverses ArrayList and removes elements not meeting a requirements (O(n), AGAIN).
   *     So, O(n^2). Not so good.
   *    *    NB: NOT OPTIMIZED - THROW IN A BETTER WAY OF FILTERING, BRAH. PREDICATES, BROSEPH.
   *    *    FUTURE YOU WILL BE THANKFUL OF THIS COMMENT IF YOU HEED IT.
   *    *
   * @return the required questions.
   */
  @Override
  public List<Question> getRequiredQuestions() {
    // convert HashMap to List of Questions
    List<Question> questionList = new ArrayList<>(this.questionnaire.values());
    // filter through questionList and make a list only of required questions
    questionList.removeIf(s -> !s.isRequired());
    return questionList;

    //return questionList.stream()
    //    .filter(q -> q.isRequired()).collect(Collectors.toList());
  }

  /**
   * Return a list of all optional questions in the questionnaire.
   *  ** SAME CODE AS GETREQUIREDQUESTIONS - SUBOPTIMAL. REDESIGN.
   * @return the optional questions.
   */
  @Override
  public List<Question> getOptionalQuestions() {
    // convert HashMap to List of Questions
    List<Question> questionList = new ArrayList<Question>(this.questionnaire.values());
    // filter through questionList and make a list only of required questions
    questionList.removeIf(s -> s.isRequired());
    return questionList;
  }

  /**
   * Report if all required questions have some non-empty response.
   *    *
   *    NB: NOT OPTIMIZED - THROW IN A BETTER WAY OF FILTERING, BRAH. PREDICATES, BROSEPH.
   *    FUTURE YOU WILL BE THANKFUL OF THIS COMMENT IF YOU HEED IT.
   *    *
   * @return true if all required questions have responses, false otherwise.
   */
  @Override
  public boolean isComplete() {
    List<Question> requiredQuestions = this.getRequiredQuestions();
    boolean flag = true;
    // traverse list of Questions, change flag to false if any required Question in list has
    // an empty or null response.
    for (int i = 0; i < requiredQuestions.size(); i++) {
      if (!isValidString(requiredQuestions.get(i).getAnswer())) {
        flag = false;
      } // if non-valid string found in answers
    } // end for loop
    return flag;
  }

  /**
   * Return a list of just the responses to all the questions in the questionnaire.
   * // map things here; can use stream or
   * @return the responses
   */
  @Override
  public List<String> getResponses() {
    // set up List of Questions to catch responses
    List<Question> allQuestions = getQuestions();
    List<String> allResponses = allQuestions.stream()
        .map(q -> q.getAnswer()).collect(Collectors.toList());
    // ALTERNATIVE METHOD: - FAILS Test Get Responses because 0 index is null...
    // List<String> allResponses = new ArrayList<>();
    // for (int i = 0; i < this.questionnaire.size(); i++) {
    //    allResponses.add(this.getQuestion(i).getAnswer());
    //  }
    return allResponses;
  }

  /**
   * Produce a new questionnaire containing just the questions where the given predicate returns
   * true. The returned questionnaire is completely independent of this questionnaire. That is,
   * the questions in the returned questionnaire are <b>copies</b> of the original questions.
   * // use predicate method .test; create list of keys; loop over keys,
   * @param pq the predicate
   * @return the new questionnaire
   */
  @Override
  public Questionnaire filter(Predicate<Question> pq) {
    // instantiate blank questionnaire
    Questionnaire newQuestionnaire = new QuestionnaireImpl();
    List<Question> originalQuestions = this.getQuestions();
    // find items in questionnaire where the predicate is true
    // List<Question> filteredQuestions =
       //     originalQuestions.stream()
         //       .filter(pq.test()).collect(Collectors.toList());
    // add all Questions to new Questionnaire with new Prompts
    for (int i = 0, j = 0; i < originalQuestions.size(); i++) {
      if (pq.test(originalQuestions.get(i))) { // test Question against Predicate
        // make a copy of each Question that passes the test
        Question copiedQuestion = originalQuestions.get(i);
        // add that copy of the Question to the new Questionnaire.
        newQuestionnaire.addQuestion("Question #" + (j + 1), copiedQuestion);
        j++; // iterate j
      } // if loop
    }
    return newQuestionnaire;
  }

  /**
   * Sort the questions according to the given comparator. Return values from
   * {@code getQuestion(int)} should reflect the new sorted order following sort.
   *
   * @param comp a comparator for Question
   */
  @Override
  public void sort(Comparator<Question> comp) {
    List<Question> allQuestions = this.getQuestions();


  }

  /**
   * Produce a single summary value based on the given folding function and
   * seed value.
   *
   * @param bf   the folding function
   * @param seed the seed value
   * @return the summary value
   */
  @Override
  public <R> R fold(BiFunction<Question, R, R> bf, R seed) {
    List<Question> allQuestions = this.getQuestions();
    R result;
    for (int i = 0; i < allQuestions.size(); i++) {
        Question value = allQuestions.get(i);
        result = bf.apply(value, seed);
    }
    //return result;
    return null;
    // use a for loop to pass though questions, use .apply(object, seed);
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
    if (string.isBlank()) {
      return false;
    }
    return true; // all other cases pass
  }
  /*
  public static void main(String [] args) {
    // create Questionnaire
    QuestionnaireImpl questionnaire = new QuestionnaireImpl();
    // create Questions
    ShortAnswer shortAnswerReq = new ShortAnswer("Required short answer", true);
    ShortAnswer shortAnswerNotReq = new ShortAnswer("Optional short answer", false);
    YesNo yesNoAnswerReq = new YesNo("Required Yes/No", true);
    YesNo yesNoAnsOpt = new YesNo("Optional Yes/No", false);
    Likert likertAnsReq = new Likert("Required Likert", true);
    Likert likertAnsOpt = new Likert("Optional Likert", false);
    // add Questions to Questionnaire
    questionnaire.addQuestion("Question 1: Required Short Answer", shortAnswerReq);
    questionnaire.addQuestion("Question 2: Optional Short Answer", shortAnswerNotReq);
    questionnaire.addQuestion("Question 3: Required Yes/No", yesNoAnswerReq);
    questionnaire.addQuestion("Question 4: Optional Yes/No", yesNoAnsOpt);
    questionnaire.addQuestion("Question 5: Required Likert", likertAnsReq);
    questionnaire.addQuestion("Question 6: Optional Likert", likertAnsOpt);

    Question question2 = questionnaire.getQuestion("Question 2: Optional Short Answer");
    System.out.println("Questionnaire size before removal: " + questionnaire.questionnaire.size());
    questionnaire.removeQuestion("Question 2: Optional Short Answer"); // should reduce size by one
    System.out.println("Questionnaire size after removal: " + questionnaire.questionnaire.size());

    Question question3 = questionnaire.getQuestion(0);
    System.out.println(question3.getPrompt());
  }
  */

  /**
   * Helper Function.
   * Returns all Questions of the Questionnaire in a List.
   * @return List of Question objects.
   */
  public List<Question> getQuestions() {
    List<Question> allQuestions = new ArrayList<>(this.questionnaire.values());
    return allQuestions;
  }




}
