package com.linguaclassica.teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.linguaclassica.access.WicketApplication;
import com.linguaclassica.entity.EntityClassModel;

public class TreeProviderEntityClass implements ITreeProvider<EntityClassModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TreeProviderEntityClass(){
		
	}
	
	@Override
	public void detach() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<EntityClassModel> getChildren(final EntityClassModel c) {
		// TODO Auto-generated method stub
		Iterator<EntityClassModel> empty = Collections.emptyIterator();
		
		List<EntityClassModel> checkList = new ArrayList<>();
		checkList.add(new EntityClassModel(1, "Latin 101", null, /*Date startdate, Date enddate, */1));
		checkList.add(new EntityClassModel(2, "Latin 102", null, /*Date startdate, Date enddate, */1));
		//checkList.add(new EntityClassModel(3, "Latin 103", null, /*Date startdate, Date enddate, */1));
		
		if(c.equals(checkList.get(0))){
			
		}
		
		//Iterator<EntityClassModel> sampleCase = List.iterator();
		
		return empty; //sampleCase;//c.getEntityClassModelList().iterator();
		//Must return a List<EntityClassModel> as they represent what will be displayed underneath each node
	}

	@Override
	public Iterator<EntityClassModel> getRoots() {
		// TODO Auto-generated method stub
		Iterator<EntityClassModel> empty = Collections.emptyIterator();
		
		List<EntityClassModel> sampleList = new ArrayList<>();
		sampleList.add(new EntityClassModel(1, "Latin 101", null, /*Date startdate, Date enddate, */1));
		sampleList.add(new EntityClassModel(2, "Latin 102", null, /*Date startdate, Date enddate, */1));
		//sampleList.add(new EntityClassModel(3, "Latin 103", null, /*Date startdate, Date enddate, */1));
		
		Iterator<EntityClassModel> sampleCase = sampleList.iterator();
		
		return sampleCase;//sampleCase;
		//WicketApplication.getTreeApplication().classes.iterator();
		//Should return the roots of each tree (nodes)
	}

	@Override
	public boolean hasChildren(EntityClassModel c) {
		// TODO Auto-generated method stub
		return true;//To actually display stuff, rewrite method
	}

	@Override
	public IModel<EntityClassModel> model(EntityClassModel c) {
		// TODO Auto-generated method stub
		return (IModel<EntityClassModel>) c;//new EntityClassModelIModel(c);
	}

	private static class EntityClassModelIModel extends LoadableDetachableModel<EntityClassModel>{

		private static final long serialVersionUID = 1L;
		
		private final int id;
		
		public EntityClassModelIModel(EntityClassModel c){
			super(c);
			
			id = c.getId();
		}
		
		@Override
		protected EntityClassModel load() {
			// TODO Auto-generated method stub
			return WicketApplication.getTreeApplication().getEntityClassModel(id);
		}
		
		@Override
		public boolean equals(Object o){
			
			if(o instanceof EntityClassModel){
				
				return ((EntityClassModel)o).getId() == id;
				
			}
			
			return false;
			
		}
		
		@Override
		public int hashCode(){
			
			return id;//id.hashCode();
			//Figure out why it produces an error
			
		}
		
	}
	
}
