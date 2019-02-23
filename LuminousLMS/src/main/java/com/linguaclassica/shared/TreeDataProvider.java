package com.linguaclassica.shared;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public class TreeDataProvider implements ITreeProvider<TreeData>
{
	private static final long serialVersionUID = 1L;

	public List<TreeData> branches; 
	
	public TreeDataProvider(List<TreeData> treeBranches)
	{
		branches = treeBranches;
	}

	@Override
	public void detach()
	{
	}

	@Override
	public Iterator<TreeData> getRoots()
	{
		return branches.iterator();
	}

	@Override
	public boolean hasChildren(TreeData branch)
	{
		return branch.getParent() == null || !branch.getBranches().isEmpty();
	}

	@Override
	public Iterator<TreeData> getChildren(final TreeData branch)
	{
		return branch.getBranches().iterator();
	}

	@Override
	public IModel<TreeData> model(TreeData branch)
	{
		return new TreeModel(branch);
	}

	private class TreeModel extends LoadableDetachableModel<TreeData>
	{
		private static final long serialVersionUID = 1L;

		private final Integer nodeId;

		public TreeModel(TreeData branch)
		{
			super(branch);

			nodeId = branch.getNodeId();
		}

		@Override
		public TreeData load()
		{
			return getBranch(nodeId);
		}

		@Override
		public boolean equals(Object obj)
		{
			if (obj instanceof TreeModel)
			{
				return ((TreeModel)obj).nodeId.equals(nodeId);
			}
			return false;
		}

		@Override
		public int hashCode()
		{
			return nodeId.hashCode();
		}
	}
	
	public TreeData getBranch(int nodeId)
	{
		return findBranch(branches, nodeId);
	}

	private TreeData findBranch(List<TreeData> branches, int nodeId)
	{
		for (TreeData branch : branches)
		{
			if (branch.getNodeId() == nodeId)
			{
				return branch;
			}

			TreeData temp = findBranch(branch.getBranches(), nodeId);
			if (temp != null)
			{
				return temp;
			}
		}
		return null;
	}
}
