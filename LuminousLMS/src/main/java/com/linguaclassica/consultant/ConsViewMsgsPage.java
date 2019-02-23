package com.linguaclassica.consultant;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NoRecordsToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.tree.TableTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.table.TreeColumn;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.HumanTheme;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.linguaclassica.access.LMSSession;
import com.linguaclassica.model.MessageModel;
import com.linguaclassica.model.UserModel;
import com.linguaclassica.service.MessageService;
import com.linguaclassica.service.UserService;
import com.linguaclassica.shared.PrivateBasePage;
import com.linguaclassica.shared.TreeData;
import com.linguaclassica.shared.TreeData.MsgStatus;
import com.linguaclassica.shared.TreeDataProvider;

public class ConsViewMsgsPage extends PrivateBasePage
{
	private static final long serialVersionUID = 1L;

	@SpringBean
	UserService userService;
	
	@SpringBean
	MessageService messageService;
	
	private Logger logger = LogManager.getLogger(getClass());
	
	private final UserModel userModel = LMSSession.get().getUser(getRequest());
	
	public ConsViewMsgsPage()
	{
		super();
		
		Integer userId = userModel.getId();

		List<Object[]> messageList;
		try
		{
			int orgid = LMSSession.get().getCurrentOrganization();
			messageList = messageService.getUsersCorrespondence(userId, orgid);
		}
		catch (Exception e)
		{
			logger.error("Unexpected error when getting messages for user: '" + userModel.getEmailAddress().trim() + "' " + e.getMessage());
			error("An error occurred. If the problem persists please contact your system administrator.");
			return;
		}
		
		// Show when there are no messages
		WebMarkupContainer wmcNone = new WebMarkupContainer("nonemessage")
		{
			private static final long serialVersionUID = 1L;
			
			public boolean isVisible()
			{
				return messageList.isEmpty();
			}
		};
		add(wmcNone);
		
		String fromStat = getString("froml");
		String toStat = getString("tol");
		String newStat = getString("newl");

		List<TreeData> msgTreeList = new ArrayList<>();
		int otherUserId = -1;
		TreeData otherUserNode = null;
		for (Object[] pair : messageList)
		{
			MessageModel message = (MessageModel) pair[0];
			UserModel otherUser = (UserModel) pair[1];

			if (otherUser.getId() != otherUserId)
			{
				/* Add a user node to the tree */
				otherUserNode = new TreeData(otherUser.getFirstName() + " " + otherUser.getLastName());
				otherUserNode.setFromTo("");
				otherUserNode.setMsgStatus(MsgStatus.CONTACT);
				otherUserNode.setDate("");
				otherUserNode.setMessage("");
				msgTreeList.add(otherUserNode);
				otherUserId = otherUser.getId();
			}
			
			/* Add a message node to the tree */
			String fromto;
			MsgStatus stat;
			if (message.getFromId() == otherUserId)
			{
				if (message.getStatus() == MessageModel.Status.UNREAD)
				{
					fromto = newStat;
					stat = MsgStatus.NEWFROM;
				}
				else
				{
					fromto = fromStat;
					stat = MsgStatus.FROM;
				}
			}
			else
			{
				fromto = toStat;
				stat = MsgStatus.TO;
			}
			TreeData msgNode = new TreeData(otherUserNode, message.getSubject());
			msgNode.setFromTo(fromto);
			msgNode.setMsgStatus(stat);
			msgNode.setDate(message.getCreateDate().toString());
			msgNode.setMessage(message.getMessage());
		}

		
		List<IColumn<TreeData, String>> columns = new ArrayList<>();
		columns.add(new TreeColumn<TreeData, String>(Model.of(getString("subjectl"))));
		columns.add(new PropertyColumn<TreeData, String>(Model.of(getString("statusl")), "fromto")
		{
           private static final long serialVersionUID = 1L;
           
           @Override
           public String getCssClass()
           {
        	   return "fromtoHdr";
           }
	    });
		columns.add(new PropertyColumn<TreeData, String>(Model.of(getString("createdatel")), "date")
		{
           private static final long serialVersionUID = 1L;
           
           @Override
           public String getCssClass()
           {
        	   return "dateHdr";
           }
	    });
		columns.add(new PropertyColumn<TreeData, String>(Model.of(getString("messagel")), "message")
		{
           private static final long serialVersionUID = 1L;
           
           @Override
           public String getCssClass()
           {
        	   return "msgHdr";
           }
	    });
		
		TreeDataProvider treeListProvider = new TreeDataProvider(msgTreeList);
        TableTree<TreeData, String> tree = new TableTree<TreeData, String>("tree", columns, treeListProvider, Integer.MAX_VALUE)
        {
            private static final long serialVersionUID = 1L;

			public boolean isVisible()
			{
				return !messageList.isEmpty();
			}
			
           @Override
            protected Component newContentComponent(String id, IModel<TreeData> model)
            {
                return new MultiLineLabel(id, new AbstractReadOnlyModel<String>()
                {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public String getObject()
                    {
                        return model.getObject().getId();
                    }
                });
            }

            @Override
            protected Item<TreeData> newRowItem(String id, int index, IModel<TreeData> model)
            {
            	Item<TreeData> item = super.newRowItem(id, index, model);
            	TreeData node = model.getObject();
            	
            	switch(node.getMsgStatus())
            	{
            		case FROM:
            			item.add(new AttributeAppender("class", Model.of("frombackground")));
            			break;
 
            		case TO:
            			item.add(new AttributeAppender("class", Model.of("tobackground")));
            			break;

            		case NEWFROM:
            			item.add(new AttributeAppender("class", Model.of("newfrombackground")));
            			break;
 
            		case CONTACT:
            			break;
            	}            	
            	return item;
            }
        };
        tree.getTable().addTopToolbar(new HeadersToolbar<>(tree.getTable(), null));
        tree.getTable().addBottomToolbar(new NoRecordsToolbar(tree.getTable()));
		tree.add(new HumanTheme());
		add(tree);
	}
}
