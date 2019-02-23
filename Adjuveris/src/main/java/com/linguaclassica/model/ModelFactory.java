package com.linguaclassica.model;

/**
 * Model factory interface for use in business / presentation layers.
 * 
 * @author Paul
 *
 */
public interface ModelFactory {

	/**
	 * Returns a new instance of the UserModel
	 * @return
	 */
	public UserModel getNewUserModel();
    
    /**
     * Returns a new instance of the SessionModel
     * @return
     */
    public SessionModel getNewSessionModel();
    
    /**
     * Returns a new instance of the MessageModel
     * @return
     */
    public MessageModel getNewMessageModel();
    
    /**
     * Returns a new instance of the ExerciseModel
     * @return
     */
    public ExerciseModel getNewExerciseModel();
    
    /**
     * Returns a new instance of the ExerCategoryModel
     * @return
     */
	public ExerCategoryModel getNewExerCategoryModel();
    
    /**
     * Returns a new instance of the InstitutionModel
     * @return
     */
	public InstitutionModel getNewInstitutionModel();
    
    /**
	 * Returns a new instance of the InstUserPermissionModel
	 * @return
	 */
	public InstUserPermissionModel getNewInstUserPermissionModel();
    
    /**
     * Returns a new instance of the QuestionModel
     * @return
     */
    public QuestionModel getNewQuestionModel();
    
    /**
     * Returns a new instance of the UsersGroupsModel
     * @return
     */
    public UsersGroupsModel getNewUsersGroupsModel();
    
    /**
     * Returns a new instance of the GroupModel
     * @return
     */
    public GroupModel getNewGroupModel();
    
    /**
     * Returns a new instance of the ClassUsersModel
     * @return
     */
    public ClassUsersModel getNewClassUsersModel();
    
    /**
     * Returns a new instance of the ClassAssignmentResultsModel
     * @return
     */
    public ClassAssignmentResultsModel getNewClassAssignmentResultsModel();
    
    /**
     * Returns a new instance of the ClassModel
     * @return
     */
    public ClassModel getNewClassModel();
    
    /**
     * Returns a new instance of the AssignmentModel
     * @return
     */
    public AssignmentModel getNewAssignmentModel();
    
    /**
     * Returns a new instance of the AssignmentsFactModel
     * @return
     */
    public AssignmentFactModel getNewAssignmentFactModel();
    
    /**
     * Returns a new instance of the AssignmentClassesModel
     * @return
     */
    public AssignmentClassesModel getNewAssignmentClassesModel();

    /**
     * Returns a new instance of the NotificationModel
     * @return
     */
  
    public NotificationModel getNewNotificationModel();
    
    /**
     * Returns a new instance of the NotificationClassesModel
     * @return
     */
    public NotificationClassesModel getNewNotificationClassesModel();

    /**
     * Returns a new instance of the PermissionModel
     * @return
     */
    public PermissionModel getNewPermissionModel();
    
    /**
     * Returns a new instance of the ProficiencyModel
     * @return
     */
    public ProficiencyModel getNewProficiencyModel();
    
    /**
     * Returns a new instance of the StudentAnswerModel
     * @return
     */
    public StudentAnswerModel getNewStudentAnswerModel();

    /**
     * Returns a new instance of the StudentAssignmentResultsModel
     * @return
     */
	public StudentAssignmentResultsModel getNewStudentAssignmentResultsModel();
    
    /**
     * Returns a new instance of the PanelsModel
     * @return
     */
	public PanelsModel getNewPanelsModel();

    /**
     * Returns a new instance of the TermModel
     * @return
     */
	public TermModel getNewTermModel();

    /**
     * Returns a new instance of the TermStudentsModel
     * @return
     */
	public TermStudentsModel getNewTermStudentsModel();

    /**
     * Returns a new instance of the TermTeachersModel
     * @return
     */
	public TermTeachersModel getNewTermTeachersModel();

    /**
     * Returns a new instance of the TermTaModel
     * @return
     */
	public TermTaModel getNewTermTaModel();
	
	/**
     * Returns a new instance of the TextPassageModel
     * @return
     */
	TextPassageModel getNewTextPassageModel();
	
	/**
     * Returns a new instance of the AnswerModel
     * @return
     */
	AnswerModel getNewAnswerModel();
	
	/**
     * Returns a new instance of the QuestionGroupModel
     * @return
     */
	QuestionGroupModel getNewQuestionGroupModel();
	
	/**
     * Returns a new instance of the QuestionQuestionGroupModel
     * @return
     */
	QuestionQuestionGroupModel getNewQuestionQuestionGroupModel();

	/**
     * Returns a new instance of the TeacherPreResultsModel
     * @return
     */
	TeacherPreResultsModel getNewTeacherPreResultsModel();
    
	/**
     * Returns a new instance of the TeacherAssignmentResultsModel
     * @return
     */
	TeacherAssignmentResultsModel getNewTeacherResultsModel();

}
