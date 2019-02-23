package com.linguaclassica.entity;

import com.linguaclassica.model.AssignmentModel;
import com.linguaclassica.model.AssignmentSequencesModel;
import com.linguaclassica.model.ClientsAssignmentsModel;
import com.linguaclassica.model.ConsultantClientsModel;
import com.linguaclassica.model.ContentItemsModel;
import com.linguaclassica.model.ExamModel;
import com.linguaclassica.model.MessageModel;
import com.linguaclassica.model.ModelFactory;
import com.linguaclassica.model.NotificationClassesModel;
import com.linguaclassica.model.NotificationModel;
import com.linguaclassica.model.OrgModel;
import com.linguaclassica.model.OrgUserPermissionModel;
import com.linguaclassica.model.OrgsResourcesModel;
import com.linguaclassica.model.OrgsContentItemsModel;
import com.linguaclassica.model.PermissionModel;
import com.linguaclassica.model.ResourceModel;
import com.linguaclassica.model.SessionModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.model.UsersGroupsModel;
import com.linguaclassica.model.VideoModel;

import org.springframework.stereotype.Component;

/**
 * Implementation of the ModelFactory interface that returns Entity objects for use in the persistence layer
 * @author Paul
 *
 */
@Component("modelFactory")
public class EntityModelFactory implements ModelFactory {

	public EntityModelFactory() {
		// TODO Auto-generated constructor stub
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
	public AssignmentModel getNewAssignmentModel() {
		return new EntityAssignmentModel();
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
	public OrgUserPermissionModel getNewOrgUserPermissionModel() {
		return new OrgUserPermissionEntity();
	}

	@Override
	public UsersGroupsModel getNewUsersGroupsModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrgModel getNewOrgModel() {
		return new EntityOrgModel();
	}

	@Override
	public VideoModel getNewVideoModel() {
		return new EntityVideoModel();
	}

	@Override
	public OrgsContentItemsModel getNewOrgsContentItemsModel() {
		return new EntityOrgsContentItemsModel();
	}

	@Override
	public ResourceModel getNewResourceModel() {
		return new EntityResourceModel();
	}

	@Override
	public OrgsResourcesModel getNewOrgsResourcesModel() {
		return new EntityOrgsResourcesModel();
	}

	@Override
	public ClientsAssignmentsModel getNewClientsAssignmentsModel() {
		return new EntityClientsAssignmentsModel();
	}

	@Override
	public ConsultantClientsModel getNewConsultantClientsModel() {
		// TODO Auto-generated method stub
		return new EntityConsultantClientsModel();
	}

	@Override
	public ContentItemsModel getNewContentItemsModel() {
		return new EntityContentItemsModel();
	}

	@Override
	public AssignmentSequencesModel getNewAssignmentSequencesModel() {
		return new EntityAssignmentSequencesModel();
	}


	@Override
	public ExamModel getNewExamModel() {
		return new EntityExamModel();
	}
	
	@Override
	public EntityOtherResourceModel getNewOtherResourceModel() {
		return new EntityOtherResourceModel();
	}

	@Override
	public EntityClientSequenceProgressModel getNewClientSequenceProgressModel() {
		return new EntityClientSequenceProgressModel();
	}

	@Override
	public EntityClientSequenceUsageModel getNewClientSequenceUsageModel() {
		return new EntityClientSequenceUsageModel();
	}
}
