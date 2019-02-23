package com.linguaclassica.entity;
import com.linguaclassica.model.AnswerModel;
import com.linguaclassica.model.AssignmentClassesModel;
import com.linguaclassica.model.AssignmentModel;
import com.linguaclassica.model.ClassAssignmentResultsModel;
import com.linguaclassica.model.AssignmentFactModel;
import com.linguaclassica.model.ClassModel;
import com.linguaclassica.model.ClassUsersModel;
import com.linguaclassica.model.ExerCategoryModel;
import com.linguaclassica.model.ExerciseModel;
import com.linguaclassica.model.GroupModel;
import com.linguaclassica.model.InstUserPermissionModel;
import com.linguaclassica.model.InstitutionModel;
import com.linguaclassica.model.MessageModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.NotificationClassesModel;
import com.linguaclassica.model.NotificationModel;
import com.linguaclassica.model.PanelsModel;
import com.linguaclassica.model.PermissionModel;
import com.linguaclassica.model.ProficiencyModel;
import com.linguaclassica.model.QuestionGroupModel;
import com.linguaclassica.model.QuestionModel;
import com.linguaclassica.model.QuestionQuestionGroupModel;
import com.linguaclassica.model.StudentAnswerModel;
import com.linguaclassica.model.SessionModel;
import com.linguaclassica.model.StudentAssignmentResultsModel;
import com.linguaclassica.model.TeacherAssignmentResultsModel;
import com.linguaclassica.model.TeacherPreResultsModel;
import com.linguaclassica.model.TermModel;
import com.linguaclassica.model.TermStudentsModel;
import com.linguaclassica.model.TermTaModel;
import com.linguaclassica.model.TermTeachersModel;
import com.linguaclassica.model.TextPassageModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.model.UsersGroupsModel;

import org.springframework.stereotype.Component;

/**
 * Implementation of the ModelFactory interface that returns Entity objects for use in the persistence layer
 * @author Paul
 *
 */
@Component("modelFactory")
public class EntityModelFactory implements ModelFactory {

	public EntityModelFactory() {
	}

	@Override
	public UserModel getNewUserModel() {
		return new EntityUserModel();
	}

	@Override
	public MessageModel getNewMessageModel() {
		return  new EntityMessageModel();
	}

	@Override
	public SessionModel getNewSessionModel() {
		return new EntitySessionModel();
	}

	@Override
	public ExerciseModel getNewExerciseModel() {
		return new EntityExerciseModel();
	}

	@Override
	public ExerCategoryModel getNewExerCategoryModel() {
		return new EntityExerCategoryModel();
	}

	@Override
	public InstitutionModel getNewInstitutionModel() {
		return new EntityInstitutionModel();
	}

	@Override
	public InstUserPermissionModel getNewInstUserPermissionModel()
	{
		return new InstUserPermissionEntity();
	}

	@Override
	public QuestionModel getNewQuestionModel() {
		return new EntityQuestionModel();
	}

	@Override
	public UsersGroupsModel getNewUsersGroupsModel() {
		return new EntityUsersGroupsModel();
	}

	@Override
	public GroupModel getNewGroupModel() {
		return new EntityGroupModel();
	}

	@Override
	public ClassUsersModel getNewClassUsersModel() {
		return new EntityClassUsersModel();
	}

	@Override
	public ClassAssignmentResultsModel getNewClassAssignmentResultsModel() {
		return new EntityClassAssignmentResultsModel();
	}

	@Override
	public ClassModel getNewClassModel() {
		return new EntityClassModel();
	}

	@Override
	public AssignmentModel getNewAssignmentModel() {
		return new EntityAssignmentModel();
	}

	@Override
	public AssignmentFactModel getNewAssignmentFactModel() {
		return new EntityAssignmentFactModel();
	}

	@Override
	public AssignmentClassesModel getNewAssignmentClassesModel() {
		return new EntityAssignmentClassesModel();
	}
	
	@Override
	public NotificationModel getNewNotificationModel() {
		return new EntityNotificationModel();
	}

	@Override
	public NotificationClassesModel getNewNotificationClassesModel() {
		return new EntityNotificationClassesModel();
	}
	
	@Override
	public PermissionModel getNewPermissionModel() {
		return new EntityPermissionModel();
	}

	@Override
	public ProficiencyModel getNewProficiencyModel() {
		return new EntityProficiencyModel();
	}

	@Override
	public StudentAnswerModel getNewStudentAnswerModel() {
		return new EntityStudentAnswerModel();
	}
	
	@Override
	public StudentAssignmentResultsModel getNewStudentAssignmentResultsModel() {
		return new EntityStudentAssignmentResultsModel();
	}

	@Override
	public PanelsModel getNewPanelsModel() {
		return new EntityPanelsModel();
	}
	
	@Override
	public TermModel getNewTermModel() {
		return new EntityTermModel();
	}
	
	@Override
	public TermStudentsModel getNewTermStudentsModel() {
		return new EntityTermStudentsModel();
	}
	
	@Override
	public TermTeachersModel getNewTermTeachersModel() {
		return new EntityTermTeachersModel();
	}
	
	@Override
	public TermTaModel getNewTermTaModel() {
		return new EntityTermTaModel();
	}

	@Override
	public TextPassageModel getNewTextPassageModel() {
		return new EntityTextPassageModel();
	}
	
	@Override
	public AnswerModel getNewAnswerModel() {
		return new EntityAnswerModel();
	}
	
	@Override
	public QuestionGroupModel getNewQuestionGroupModel() {
		return new EntityQuestionGroupModel();
	}
	
	@Override
	public QuestionQuestionGroupModel getNewQuestionQuestionGroupModel() {
		return new EntityQuestionQuestionGroupModel();
	}
	
	@Override
	public TeacherPreResultsModel getNewTeacherPreResultsModel() {
		return new EntityTeacherPreResultsModel();
	}

	@Override
	public TeacherAssignmentResultsModel getNewTeacherResultsModel() {
		return new EntityTeacherAssignmentResultsModel();
	}

}
