package com.linguaclassica.access;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.linguaclassica.entity.EntityClassModel;
import com.linguaclassica.model.AnswerModel;
import com.linguaclassica.model.UserModel;

/**
 * A Wicket WebSession object that creates the session for the Wicket 
 * access management classes only
 * @author Eugene Price
 * @Copyright("2012 - 2017 Lingua Classica")
 */

public class AlphPlusSession extends WebSession {

	private static final long serialVersionUID = 1L;

	public static AlphPlusSession get() {
		return (AlphPlusSession) Session.get();
	}
	
	private int teacherLandingPageTabIndex = 0;
	private boolean teacherResultsCalculated = false;
	private int studentLandingPageTabIndex = 0;
	private int adminLandingPageTabIndex = 0;
	private int assistantLandingPageTabIndex = 0;
	private String timezone;
	private List<EntityClassModel> selectedClasses = null;
	private List<Integer> selectedClassIDs = null;
	
	private int exerciseCategory;
	private int exerciseId;
	private int count;
	private int questionId;
	private String selectedVal;
	
    private int userPageTermId = -1;
	
	private int questionGroupId;
	private int multipleAnswersCount;
	private Boolean useUnsavedAnswers = false;
	private String unsavedQuestion;
	private List<AnswerModel>  unsavedAnswers = null;
	
	//For List matching question:
	private int listMatchingPairCount;
	private boolean useUnsavedLMs = false;
	private List<String> unSavedLMQuestions = null;
	private List<String> unSavedLMAnswers = null;
	private List<String> listMatchingPairNumbers = null;
	
	private int passageId;
	private String passageTxt = "";
	private String passageWrd = "";
	private int wordnumber = 0;
	private int linenumber = 0;
	
	
	private Boolean editOption;
	private Boolean addOption;
	
	private boolean multiplePermissions = false;
	private Integer currentIUP = 0;
	private Integer currentInstitutionId = 0;
	private Integer currentPermissionId = 0;
	
	private Integer currentTermID = 0;
	private Integer currentClassID = 0;
	private String dateFormat;
	private String timeFormat;
	
	// The number of past terms to display for institution administrators.
	private int dateRange;
	public static final int BEGINNING_OF_TIME = -1;
	
    // Beginning and end terms for display in InstAdminTeachersPanel and InstAdminResultsPanel 
	private int teacherResultsBegin, teacherResultsEnd;

	public AlphPlusSession(Request request) {
		super(request);
	}
	/*
	public UserIdx getUserIdx() {
		return userIdx;
	}
	*/
	/**
	 * Retrieve the active user from the session
	 * @param request
	 * @return An instance of the user model or NULL if no active user is defined
	 */
	public UserModel getUser(Request request) {
		System.out.println("AlphPlusSession, getUser");
		// Access to the underlying HTTP container is only available through the request
		HttpServletRequest servletRequest = (HttpServletRequest)request.getContainerRequest();
		HttpSession httpSession = servletRequest.getSession();
		
		if(httpSession != null){
			System.out.println("session object not null");
		}
		
		// Get the user model from the container session
		UserModel userModel = (UserModel) httpSession.getAttribute(UserModel.LCUSER);
		if(userModel != null){
			System.out.println("userModel NOT null");
		}
		return userModel;		
	}


	/**
	 * Set the current user for this session
	 * @param request - The current request being processed
	 * @param user - User to set as active in session
	 */
	public void setUser(Request request, UserModel user) {
		
		// Access to the underlying HTTP container is only available through the request
		HttpServletRequest servletRequest = (HttpServletRequest)request.getContainerRequest();
		HttpSession httpSession = servletRequest.getSession();

		// Set the user in the session. We use the underlying container session rather than
		// the wicket session as the user is also needed by the parser and this way we only
		// have one copy of the data.
		httpSession.setAttribute(UserModel.LCUSER, user);
	}
	
	
	/*
	 * The below, commented out for now, shows how we might use spring to call an .xml configuration file.
	 * We do not have a use for this at present, but here it is if we need it in the future.
	 */
	//ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	
	
	public int getTeacherLandingPageTabIndex() {
		return teacherLandingPageTabIndex;
	}
	public void setTeacherLandingPageTabIndex(int teacherLandingPageTabIndex) {
		this.teacherLandingPageTabIndex = teacherLandingPageTabIndex;
	}
	public int getStudentLandingPageTabIndex() {
		return studentLandingPageTabIndex;
	}
	public void setStudentLandingPageTabIndex(int studentLandingPageTabIndex) {
		this.studentLandingPageTabIndex = studentLandingPageTabIndex;
	}
	public int getAdminLandingPageTabIndex() {
		return adminLandingPageTabIndex;
	}
	public void setAdminLandingPageTabIndex(int adminLandingPageTabIndex) {
		this.adminLandingPageTabIndex = adminLandingPageTabIndex;
	}
	public int getAssistantLandingPageTabIndex() {
		return assistantLandingPageTabIndex;
	}
	public void setAssistantLandingPageTabIndex(int assistantLandingPageTabIndex) {
		this.assistantLandingPageTabIndex = assistantLandingPageTabIndex;
	}
	public List<EntityClassModel> getSelectedClasses() {
		return selectedClasses;
	}
	public void setSelectedClasses(List<EntityClassModel> selectedClasses) {
		this.selectedClasses = selectedClasses;
	}
	
	public List<Integer> getSelectedClassIDs()
	{
		if (selectedClassIDs == null)
		{
			// not used yet, return empty
			return new ArrayList<Integer>();
		}
		else if ((selectedClassIDs.size() > 0) && (selectedClassIDs.get(selectedClassIDs.size() - 1) == 0))
		{
			// remove the special ID (id == 0 means select All) from the end
			List<Integer> usableIDs = new ArrayList<Integer>(selectedClassIDs);
			usableIDs.remove(usableIDs.size() - 1);
			return usableIDs;
		}
		else
		{
			// use list as is
			return selectedClassIDs;
		}
	}

	public void setSelectedClassIDs(List<Integer> classIDs) 
	{
		selectedClassIDs = classIDs;
	}
	
	public String getTimeZone()
	{
		return timezone;
	}
	
	public void setTimeZone(String tz)
	{
		timezone = tz;
	}
	
	/*
	 * Sets/gets variables for EditExercisePage
	 */
	public void setExerciseCategory(Integer exerciseCategory) {
		this.exerciseCategory = exerciseCategory;
	}
	
	public Integer getExerciseCategory() {
		return exerciseCategory;
	}
	
	public void setExerciseId(Integer exerciseId) {
		this.exerciseId = exerciseId;
	}
	
	public Integer getExerciseId() {
		return exerciseId;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getCount() {
		return count;
	}
	
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
	public Integer getQuestionId() {
		return questionId;
	}
	
	public void setSelectedVal(String selectedVal) {
		this.selectedVal = selectedVal;
	}
	
	public String getSelectedVal() {
		return selectedVal;
	}
	
	public void setPassageId(int passageId) {
		this.passageId = passageId;
	}
	
	public int getPassageId() {
		return passageId;
	}
	
	public void setPassageTxt(String passageTxt) {
		this.passageTxt = passageTxt;
	}
	
	public String getPassageTxt() {
		return passageTxt;
	}
	
	public void setPassageWrd(String passageWrd) {
		this.passageWrd = passageWrd;
	}
	
	public String getPassageWrd() {
		return passageWrd;
	}
	public Integer getLinenumber(){
		return linenumber;
	}
	
	public void setLinenumber(Integer linenumber){
		this.linenumber = linenumber;
	}
	
	public Integer getWordnumber(){
		return wordnumber;
	}
	
	public void setWordnumber(Integer wordnumber){
		this.wordnumber = wordnumber;
	}
	
	public void setEditOption(Boolean editOption) {
		this.editOption = editOption;
	}
	
	public Boolean getEditOption() {
		return editOption;
	}
	
	public void setAddOption(Boolean addOption) {
		this.addOption = addOption;
	}
	
	public Boolean getAddOption() {
		return addOption;
	}
	
	public void setQuestionGroupId(Integer questionGroupId) {
		this.questionGroupId = questionGroupId;
	}
	
	public Integer getQuestionGroupId() {
		return questionGroupId;
	}
	public boolean getTeacherResultsCalculated() {
		return teacherResultsCalculated;
	}
	public void setTeacherResultsCalculated(boolean teacherResultsCalculated) {
		this.teacherResultsCalculated = teacherResultsCalculated;
	}
	public int getMultipleAnswersCount() {
		return multipleAnswersCount;
	}
	public void setMultipleAnswersCount(int multipleAnswersCount) {
		this.multipleAnswersCount = multipleAnswersCount;
	}
	public String getUnsavedQuestion() {
		return unsavedQuestion;
	}
	public void setUnsavedQuestion(String unsavedQuestion) {
		this.unsavedQuestion = unsavedQuestion;
	}
	public List<AnswerModel> getUnsavedAnswers() {
		return unsavedAnswers;
	}
	public void setUnsavedAnswers(List<AnswerModel> unsavedAnswers) {
		this.unsavedAnswers = unsavedAnswers;
	}
	public Boolean getUseUnsavedAnswers() {
		return useUnsavedAnswers;
	}
	public void setUseUnsavedAnswers(Boolean useUnsavedAnswers) {
		this.useUnsavedAnswers = useUnsavedAnswers;
	}
	
	//For List matching question:
	public int getListMatchingPairCount(){
		return listMatchingPairCount;
	}
	public void setListMatchingPairCount(int listMatchingPairCount){
		this.listMatchingPairCount = listMatchingPairCount;
	}
	public boolean getUseUnsavedLMs(){
		return useUnsavedLMs;
	}
	public void setUseUnsavedLM(boolean useUnsavedLMs){
		this.useUnsavedLMs = useUnsavedLMs;
	}
	public List<String> getUnSavedLMQuestions() {
		return unSavedLMQuestions;
	}
	public void setUnSavedLMQuestions(List<String> unSavedLMQuestions){
		this.unSavedLMQuestions = unSavedLMQuestions;
	}
	public List<String> getUnSavedLMAnswers() {
		return unSavedLMQuestions;
	}
	public void setUnSavedLMAnswers(List<String> unsavedLMAnswers){
		unSavedLMAnswers = unsavedLMAnswers;
	}
	public List<String> getListMatchingPairNumbers(){
		return listMatchingPairNumbers;
	}
	public void setListMatchingPairNumbers(List<String> listMatchingPairNumbers){
		this.listMatchingPairNumbers = listMatchingPairNumbers;
	}
	
	public boolean areMultiplePermissions()
	{
		return multiplePermissions;
	}
	
	public void setMultiplePermissions(boolean b)
	{
		multiplePermissions = b;
	}
	
	public Integer getCurrentIUP()
	{
		return currentIUP;
	}
	
	public void setCurrentIUP(Integer iupId)
	{
		currentIUP = iupId;
	}
	
	public Integer getCurrentInstitution()
	{
		return currentInstitutionId;
	}
	
	public void setCurrentInstitution(Integer iid)
	{
		currentInstitutionId = iid;
	}
	
	public Integer getCurrentPermission()
	{
		return currentPermissionId;
	}
	
	public void setCurrentPermission(Integer pid)
	{
		currentPermissionId = pid;
	}
	
	public Integer getCurrentTerm()
	{
		return currentTermID;
	}
	
	public void setCurrentTerm(Integer termId)
	{
		currentTermID = termId;
	}
	
	public Integer getCurrentClass()
	{
		return currentClassID;
	}
	
	public void setCurrentClass(Integer classId)
	{
		currentClassID = classId;
	}
	
	public String getDateFormat()
	{
		return dateFormat;
	}
	
	public void setDateFormat(String df)
	{
		dateFormat = df;
	}
	
	public String getTimeFormat()
	{
		return timeFormat;
	}
	
	public void setTimeFormat(String tf)
	{
		timeFormat = tf;
	}
	
	public void setUserPageTermId(int userPageTermId) {
		this.userPageTermId = userPageTermId;
	}

	public int getUserPageTermId() {
	return userPageTermId;
	}
	
	// For time limiting displayed items
	public int getDateRange(){
		return dateRange;
	}
	public void setDateRange(int dateRange){
		this.dateRange = dateRange;
	}

	public int getTeacherResultsBegin(){
		return teacherResultsBegin;
	}
	public void setTeacherResultsBegin(int teacherResultsBegin){
		this.teacherResultsBegin = teacherResultsBegin;
	}

	public int getTeacherResultsEnd(){
		return teacherResultsEnd;
	}
	public void setTeacherResultsEnd(int teacherResultsEnd){
		this.teacherResultsEnd = teacherResultsEnd;
	}
}

